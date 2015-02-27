package controllers

import play.api.mvc._
import views.html.{magazine => view}

import models._
import com.github.aselab.activerecord.dsl._

object Magazines extends Controller {

  def index = Action {
    Ok(view.index(Magazine.all.toList))
  }

  def show(id: Long) = Action {
    Magazine.find(id) match {
      case Some(magazine) => Ok(view.show(magazine))
      case _ => NotFound
    }
  }

  def newPage = Action { implicit request =>
    Ok(view.edit(Magazine.form, routes.Magazines.create, "Create", "Magazine create"))
  }

  def create = Action { implicit request =>
    Magazine.form.bindFromRequest.fold(
      errors => BadRequest(view.edit(errors, routes.Magazines.create, "Create", "Magazine create")), {
      magazine =>
        Magazine.transaction { magazine.save }
        Redirect(routes.Magazines.show(magazine.id))
    })
  }

  def edit(id: Long) = Action { implicit request =>
    Magazine.find(id) match {
      case Some(magazine) => Ok(view.edit(Magazine.form(magazine), routes.Magazines.update(id), "Update", "Magazine edit"))
      case _ => NotFound
    }
  }

  def update(id: Long) = Action { implicit request =>
    Magazine.find(id) match {
      case Some(magazine) =>
        Magazine.form(magazine).bindFromRequest.fold(
          errors => BadRequest(view.edit(errors, routes.Magazines.update(id), "Update", "Magazine edit")), {
          magazine =>
            Magazine.transaction { magazine.save }
            Redirect(routes.Magazines.index)
        })
      case _ => NotFound
    }
  }

  def delete(id: Long) = Action {
    Magazine.find(id) match {
      case Some(magazine) =>
        Magazine.transaction { magazine.delete }
        Ok
      case _ => NotFound
    }
  }
}
