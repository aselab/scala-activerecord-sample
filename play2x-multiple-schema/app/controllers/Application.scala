package controllers

import javax.inject.Inject

import play.api.mvc._
import play.api.i18n.{MessagesApi, I18nSupport}
import org.webjars.play.WebJarsUtil

class Application @Inject()(components: ControllerComponents)(implicit webJarsUtil: WebJarsUtil)
  extends AbstractController(components) with I18nSupport {

  def index = Action { implicit request =>
    Ok(views.html.index("Hello, Scala ActiveRecord"))
  }

}
