package helpers

import com.github.aselab.activerecord._

object FormHelper {
  import ReflectionUtil._
  implicit val locale = java.util.Locale.getDefault

  val t = i18n.DefaultTranslator

  def field[T <: ActiveRecord](name: String, model: T)(implicit m: Manifest[T])=
  {
    val value = (model.getValue[Any](name) match {
      case v: Option[_] => v
      case v => Option(v)
    }).map(_.toString).getOrElse("")

    val hasError = model.errors.exists(name)
    val divClass = if (hasError) "control-group error" else "control-group"

    <div class={divClass}>
      <label for={name} class="control-label">{t.translateField(m.erasure, name)}</label>
      <div class="controls">
        <input type={if (name.contains("password")) "password" else "text"} id={name} name={name} class="input-xlarge" value={value} />
        {if (hasError)
        <span class="help-inline">{model.errors.get(name).map(e => t.translateMessage(e.message, e.args:_*)).mkString(", ")}</span>
        }
      </div>
    </div>
  }
}
