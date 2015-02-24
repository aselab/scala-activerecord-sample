package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models._
import views.html.{question => view}

object Questions extends Controller {
  def newPage = Action { implicit request =>
    Ok(view.edit(Question.form))
  }

  def create = Action { implicit request =>
    Question.form.bindFromRequest.fold(
      errors => BadRequest(view.edit(errors)), {
      question =>
        Redirect(routes.Application.index).flashing(
          "success" -> "The question has been created",
          "message" -> question.message
        )
    })
  }
}

