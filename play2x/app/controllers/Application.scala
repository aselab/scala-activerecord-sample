package controllers

import javax.inject.Inject

import play.api.mvc._
import play.api.i18n.{MessagesApi, I18nSupport}

class Application @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index = Action { implicit request =>
    Ok(views.html.index("Hello, Scala ActiveRecord"))
  }

}
