package helpers

import com.github.aselab.activerecord._
import java.util.Locale

import scala.xml._
import mojolly.inflector.InflectorImports._

trait FormHelper {
  implicit def toExtendElem(elem: Elem) = new {
    object Attr {
      def apply(key: String, value: Any): xml.MetaData = Option(value).map { v =>
        xml.Attribute("", key, v.toString, xml.Null)
      }.getOrElse(xml.Null)
    }

    def attr(key: String, value: Any): Elem = attr((key, value))

    def attr(kv: (String, Any)*): Elem = {
      kv.map{ case (k, v) => Attr(k, v) }.foldLeft(elem) {
        case (xml, attr) => xml % attr
      }
    }
  }

  implicit def toExtendNodeSeq(nodeSeq: NodeSeq) = new {
    def \(node: Node*): NodeSeq = nodeSeq ++ node
  }

  def div: Elem = div(Nil)
  def div(str: String): Elem = <div>{str}</div>
  def div(xml: => NodeSeq): Elem = <div>{xml}</div>
  val input: Elem = <input/>
  def ol(xml: => NodeSeq): Elem = <ol>{xml}</ol>
  def li(xml: => NodeSeq): Elem = <li>{xml}</li>
  def li(str: String): Elem = <li>{str}</li>
  def span(xml: => NodeSeq): Elem = <span>{xml}</span>
  def span(str: String): Elem = <span>{str}</span>
  def p(xml: => NodeSeq): Elem = <p>{xml}</p>
  def p(str: String): Elem = <p>{str}</p>
  def label(xml: => NodeSeq): Elem = <label>{xml}</label>
  def label(str: String): Elem = <label>{str}</label>
  def fieldset(xml: => NodeSeq): Elem = <fieldset>{xml}</fieldset>
  def form(xml: => NodeSeq): Elem = <form>{xml}</form>
  def select(xml: => NodeSeq): Elem = <select>{xml}</select>
  def option(str: String): Elem = <option>{str}</option>
}

object BaseFormHelper extends FormHelper

trait BaseInputData {
  val model: ActiveRecord
  val key: String
  val label: String
  implicit val locale: Locale

  import ReflectionUtil._
  lazy val className = model.getClass.getName.split("\\.").last.camelize
  lazy val id = "%s_%s".format(className, key)
  lazy val name = key
  lazy val outerId = id + "_input"
  lazy val value = (model.getValue[Any](key) match {
    case v: Option[_] => v
    //case v: ActiveRecordManyToMany[_, _] => Option(v.iterator.map(_.getValue[Any]("name")).toList)
    case v => Option(v)
  }).map(_.toString).getOrElse("")
  lazy val outerClass = "string input optinal stringish"
  lazy val inputAttributes = Seq(("id", id), ("maxlength", "255"), ("name", name), ("type", "text"), ("value", value))
  lazy val errors = model.errors.get(key).map(_.message)
  lazy val hasError = errors.nonEmpty
}

trait InputDataFactory {
  case class InputData(model: ActiveRecord, key: String, label: String, locale: Locale) extends BaseInputData

  def create(model: ActiveRecord, key: String, label: String)(implicit locale: Locale): BaseInputData =
    InputData(model, key, label, locale)
}

trait DefaultFormHelper[T <: ActiveRecord] extends FormHelper {
  val t = Config.translator
  val inputDataFactory: InputDataFactory
  val model: T
  val m: Manifest[T]

  def input[T <: ActiveRecord](key: String, model: T = this.model)(implicit m: Manifest[T] = this.m, locale: Locale): Elem = {
    val label = t.field(m.erasure, key)
    input(inputDataFactory.create(model, key, label))
  }

  def input(inputData: BaseInputData): Elem = {
    li {
      label(inputData.label).attr(("class", "label"), ("for", inputData.id)) \
      BaseFormHelper.input.attr(inputData.inputAttributes: _*) \
      div(inputData.errors.mkString(", "))
    }.attr(("class", inputData.outerClass), ("id", inputData.outerId))
  }

  def submit(label: String): Elem =
    BaseFormHelper.input.attr(("class", "btn"), ("type", "submit"), ("value", label))

  def cancel =
    <a href={"/%s".format(model.getClass.getName.split("\\.").last.camelize)} class="btn">Cancel</a>
}

object FormHelper {
  case class Helper[T <: ActiveRecord](model: T, m: Manifest[T]) extends DefaultFormHelper[T] {
    val inputDataFactory = new InputDataFactory {}
  }

  def fieldset[T <: ActiveRecord](model: T)(code: => DefaultFormHelper[T] => Unit)(implicit m: Manifest[T]) = {
    code(Helper(model, m))
  }
}

package twitter.bootstrap {
  trait FormHelper[T <: ActiveRecord] extends DefaultFormHelper[T] {
    def groupClass(hasError: Boolean) = "control-group " + (if (hasError) "error " else "")

    def controls(errors: Seq[String] = Nil)(xml: => NodeSeq): Elem =
      div {
        xml ++ Option(errors.mkString(", ")).collect{
          case e if e.nonEmpty => <span class="help-inline">{e}</span>
        }.getOrElse(Nil)
      }.attr("class", "controls")

    override def submit(label: String) =
      super.submit(label).attr("class", "btn btn-primary")

    override def input(inputData: BaseInputData) = {
      div {
        label(inputData.label).attr(("class", "control-label"), ("for", inputData.id)) \
        controls(inputData.errors) {
          BaseFormHelper.input.attr(inputData.inputAttributes: _*)
        }
      }.attr(("class", groupClass(inputData.hasError) + inputData.outerClass), ("id", inputData.outerId))
    }
  }

  object FormHelper {
    case class Helper[T <: ActiveRecord](model: T, m: Manifest[T]) extends FormHelper[T] {
      val inputDataFactory = new InputDataFactory {}
    }

    def fieldset[T <: ActiveRecord](model: T)(code: => DefaultFormHelper[T] => Unit)(implicit m: Manifest[T]) =
      code(Helper(model, m))
  }
}

