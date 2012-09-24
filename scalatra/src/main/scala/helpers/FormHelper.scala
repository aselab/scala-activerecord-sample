package helpers

import com.github.aselab.activerecord._
import java.util.Locale

object FormHelper {
  import ReflectionUtil._

  val t = Config.translator

  def field[T <: ActiveRecord](name: String, model: T)(implicit m: Manifest[T], locale: Locale)=
  {
    val value = (model.getValue[Any](name) match {
      case v: Option[_] => v
      //case v: ActiveRecordManyToMany[_, _] => Option(v.iterator.map(_.getValue[Any]("name")).toList)
      case v => Option(v)
    }).map(_.toString).getOrElse("")

    val error = model.errors.get(name).map(_.message).mkString(", ")
    text(t.field(m.erasure, name), value, name, error)
  }

  def inputType(fieldInfo: FieldInfo) = {
    fieldInfo.fieldType match {
      case t: Class[Boolean]=> "checkbox"
      case _ => "text"
    }
  }

  def controls(error: String = "")(xml: => scala.xml.Elem) =
    <div class="controls">
      {xml}
      {if (error.nonEmpty) <span class="help-inline">{error}</span>}
    </div>

  def groupClass(hasError: Boolean) = "control-group" + (if (hasError) " error" else "")

  def text(label: String, value: String, name: String, error: String = "") =
    <div class={groupClass(error.nonEmpty)}>
      <label for={name} class="control-label">{label}</label>
      {controls(error) {
        <input type={if (name.contains("password")) "password" else "text"}
          id={name} name={name} class="input-xlarge" value={value} />
      }}
    </div>

  def textarea(label: String, value: String, name: String, error: String = "") =
    <div class={groupClass(error.nonEmpty)}>
      <label for={name} class="control-label">{label}</label>
      {controls(error) {
        <textarea id={name} name={name}>{value}</textarea>
      }}
    </div>

  def checkbox(label: String, name: String, id: Int, isChecked: Boolean = false, isInline: Boolean = false, error: String = "") =
    <div class={groupClass(error.nonEmpty)}>{
      controls(error) {
        <label class={"checkbox" + (if (isInline) " inline" else "")}>
          <input type="checkbox" name={name} id={name + id} value="" checked={if (isChecked) "checked" else ""}/>
          {label}
        </label>
      }
    }</div>

  def radio(label: String, name: String, id: Int, isChecked: Boolean = false, error: String = "") =
    <div class={groupClass(error.nonEmpty)}>{
      controls() {
        <label class="radio">
          <input type="radio" name={name} id={name + id} value={"option" + 1} checked={if (isChecked) "checked" else ""}/>
          {label}
        </label>
      }
    }</div>

}
