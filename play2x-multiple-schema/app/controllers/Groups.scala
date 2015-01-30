package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.schema2._
import views.html.{group => view}
import com.github.aselab.activerecord.dsl._

object Groups extends Controller {

  def index = Action {
    Ok(view.index(Group.all.toList))
  }

  def show(id: Long) = Action {
    Group.find(id) match {
      case Some(group) => Ok(view.show(group))
      case _ => NotFound
    }
  }

  def newPage = Action { implicit request =>
    Ok(view.edit(Group.form, routes.Groups.create, "Create", "group create"))
  }

  def create = Action { implicit request =>
    Group.form.bindFromRequest.fold(
      errors => BadRequest(view.edit(errors, routes.Groups.create, "Create", "group create")), {
      group =>
        Group.transaction { group.save }
        Redirect(routes.Groups.show(group.id))
    })
  }

  def edit(id: Long) = Action { implicit request =>
    Group.find(id) match {
      case Some(group) => Ok(view.edit(Group.form(group), routes.Groups.update(id), "Update", "group edit"))
      case _ => NotFound
    }
  }

  def update(id: Long) = Action { implicit request =>
    Group.find(id) match {
      case Some(group) =>
        Group.form(group).bindFromRequest.fold(
          errors => BadRequest(view.edit(errors, routes.Groups.update(id), "Update", "group edit")), {
          group =>
            Group.transaction { group.save }
            Redirect(routes.Groups.index)
        })
      case _ => NotFound
    }
  }

  def delete(id: Long) = Action {
    Group.find(id) match {
      case Some(group) =>
        Group.transaction { group.delete }
        Ok
      case _ => NotFound
    }
  }
}
