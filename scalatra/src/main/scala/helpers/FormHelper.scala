package helpers

import com.github.aselab.activerecord._
import java.util.Locale

object FormHelper {
  import reflections.ReflectionUtil._

  val t = Config.translator

  def field[T <: ActiveRecord](name: String, model: T)(implicit m: Manifest[T], locale: Locale)=
  {
    val value = (model.getValue[Any](name) match {
      case v: Option[_] => v
      case v => Option(v)
    }).map(_.toString).getOrElse("")

    val hasError = model.errors.exists(name)
    val divClass = if (hasError) "control-group error" else "control-group"

    <div class={divClass}>
      <label for={name} class="control-label">{t.field(m.erasure, name)}</label>
      <div class="controls">
        <input type={if (name.contains("password")) "password" else "text"} id={name} name={name} class="input-xlarge" value={value} />
        {if (hasError)
        <span class="help-inline">{model.errors.get(name).map(_.message).mkString(", ")}</span>
        }
      </div>
    </div>
  }
}
