package models

import com.github.aselab.activerecord._
import com.github.aselab.activerecord.dsl._

case class User(
  @Required var name: String,
  @Required @Unique var login: String,
  @Email var email: String
) extends ActiveRecord {
  @Required(on="create")
  @Length(min=8)
  @Confirmation
  @Transient
  var password: String = _

  var passwordConfirmation: String = _

  var hashedPassword: String = _

  override def beforeSave() {
    if (password != null && password != "")
      hashedPassword = User.md5digest(password)
  }
}

object User extends ActiveRecordCompanion[User] with PlayFormSupport[User] {
  val md5 = java.security.MessageDigest.getInstance("MD5")

  def md5digest(str: String): String = {
    md5.digest(str.getBytes).map("%02x".format(_)).mkString
  }

  def authenticate(login: String, password: String): Option[User] = {
    this.findBy("login" -> login, "hashedPassword" -> md5digest(password))
  }

  override lazy val helper = new PlayHelper(self) {
    import play.api.data._
    import play.api.templates.Html
    import _root_.views.html.helper._

    override protected def inputOptions(field: Field, options: Seq[(Symbol, Any)]) = {
      super.inputOptions(field, options :+ ('class -> "form-control"))
    }

    override implicit def fieldConstructor(implicit m: Manifest[User]) = new FieldConstructor {
      def apply(elements: FieldElements) = {
        val error = if (elements.hasErrors) "has-error" else ""
        Html(<div class={s"control-group ${elements.args.get('_class).getOrElse("")} ${error}"}
          id={elements.args.get('_id).map(_.toString).getOrElse(elements.id + "_field")}>
          <div class="form-group">
            <label class="control-label col-lg-3" for={elements.id}>{Config.translator.field(m.erasure, elements.field.name)(elements.lang.toLocale)}</label>
            <div class="col-lg-5">
              {xml.Unparsed(elements.input.body)}
            </div>
            {if (elements.errors.length > 0) {
              <span class="help-block">{elements.errors(elements.lang).mkString(", ")}</span>
            }}
          </div>
        </div>.toString)
      }
    }
  }
}

