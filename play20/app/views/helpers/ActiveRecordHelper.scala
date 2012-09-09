package views.helpers

import play.api.data.Form
import views.html.helper._
import views.html.helpers.activeRecordField

object ActiveRecordHelper {
  def fieldConstructor(model: Class[_]): FieldConstructor = new FieldConstructor {
    def apply(elements: FieldElements) = activeRecordField.f(elements, model)
  }

  def fieldConstructor[T](form: Form[T])(implicit m: Manifest[T]): FieldConstructor =
    fieldConstructor(m.erasure)
}
