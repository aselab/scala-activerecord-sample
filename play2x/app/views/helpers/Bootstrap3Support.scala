package com.github.aselab.activerecord.views.helpers

import com.github.aselab.activerecord._

trait Bootstrap3Support[T <: ActiveModel] { self: ActiveModelCompanion[T] with PlayFormSupport[T] =>
  override lazy val helper = new PlayHelper(self) {
    import play.api.data._
    import play.twirl.api.Html
    import _root_.views.html.helper._

    override protected def inputOptions(field: Field, options: Seq[(Symbol, Any)]) = {
      super.inputOptions(field, options :+ ('class -> "form-control"))
    }

    override implicit def fieldConstructor(implicit m: reflect.ClassTag[T]) = new FieldConstructor {
      def apply(elements: FieldElements) = {
        val error = if (elements.hasErrors) "has-error" else ""
        Html(<div class={s"control-group ${elements.args.get('_class).getOrElse("")} ${error}"}
          id={elements.args.get('_id).map(_.toString).getOrElse(elements.id + "_field")}>
          <div class="form-group">
            <label class="control-label col-lg-3" for={elements.id}>{elements.args.get('_label).getOrElse(Config.translator.field(m.runtimeClass, elements.field.name)(elements.lang.toLocale))}</label>
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
