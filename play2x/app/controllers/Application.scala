package controllers

import javax.inject.Inject

import play.api.mvc._
import play.api.i18n.I18nSupport

class Application @Inject()(components: ControllerComponents)(
  implicit webJarAssets: WebJarAssets
) extends AbstractController(components) with I18nSupport {

  def index = Action { implicit request =>
    Ok(views.html.index("Hello, Scala ActiveRecord"))
  }

}
