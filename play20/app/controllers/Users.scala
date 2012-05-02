package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models._
import views.html.{user => view}
import com.github.aselab.activerecord.dsl._
import com.github.aselab.activerecord.ActiveRecord

object Users extends Controller {

  def userForm(id: Long = 0L) = Form(
    mapping(
      "name" -> nonEmptyText(maxLength = 128),
      "age" -> number(max = 999),
      "description" -> optional(text(maxLength = 3000))
    )((name, age, description) => {
      val u = User(name, age, description)
      // set id for update...
      val f = classOf[ActiveRecord].getDeclaredField("id")
      f.setAccessible(true)
      f.set(u, id)
      u
    })(User.unapply)
  )

  def index = Action {
    Ok(view.index(User.all.toList))
  }

  def show(id: Long) = Action {
    User(id) match {
      case Some(user) => Ok(view.show(user))
      case _ => NotFound
    }
  }

  def newPage = Action {
    Ok(view.edit(userForm(), routes.Users.create, "Create", "User create"))
  }

  def create = Action { implicit request =>
    userForm().bindFromRequest.fold(
      errors => BadRequest(view.edit(errors, routes.Users.create, "Create", "User create")), {
      user =>
        transaction { user.save }
        Redirect(routes.Users.show(user.id))
    })
  }

  def edit(id: Long) = Action {
    User(id) match {
      case Some(user) => Ok(view.edit(userForm(id).fill(user), routes.Users.update(id), "Update", "User edit"))
      case _ => NotFound
    }
  }

  def update(id: Long) = Action { implicit request =>
    userForm(id).bindFromRequest.fold(
      errors => BadRequest(view.edit(errors, routes.Users.update(id), "Update", "User edit")), {
      user =>
        transaction { user.save }
        Redirect(routes.Users.index)
    })
  }

  def delete(id: Long) = Action {
    User(id) match {
      case Some(user) =>
        transaction { user.delete }
        Ok
      case _ => NotFound
    }
  }

}
