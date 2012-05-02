package views.helpers

import views.html.helpers.bootstrapField
import views.html.helper.FieldConstructor

object BootstrapHelper {
  implicit val bootstrapConstructor = FieldConstructor(bootstrapField.f)
}
