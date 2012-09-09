package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models._
import views.html.{user => view}
import com.github.aselab.activerecord.dsl._
import com.github.aselab.activerecord.ActiveRecord

object Users extends Controller {

  def UserFormatter(user: User) = new format.Formatter[User] {
    def bind(key: String, data: Map[String, String]): Either[Seq[FormError], User] = {
      val u = User.bind(data)(user)
      if (u.validate) {
        Right(u)
      } else {
        Left(u.errors.toSeq.map(e => FormError(e.key, e.error, e.args.toSeq)))
      }
    }

    def unbind(key: String, user: User): Map[String, String] = {
      if (key.isEmpty) {
        User.unbind(user)
      } else {
        User.unbind(user).map {
          case (k, v) => ("%s[%s]".format(key, k), v)
        }
      }
    }
  }

  def userForm(user: User = User.newInstance) = Form(
    of[User](UserFormatter(user))
  ).fill(user)

  def index = Action {
    Ok(view.index(User.all.toList))
  }

  def show(id: Long) = Action {
    User(id) match {
      case Some(user) => Ok(view.show(user))
      case _ => NotFound
    }
  }

  def newPage = Action { implicit request =>
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

  def edit(id: Long) = Action { implicit request =>
    User(id) match {
      case Some(user) => Ok(view.edit(userForm(user), routes.Users.update(id), "Update", "User edit"))
      case _ => NotFound
    }
  }

  def update(id: Long) = Action { implicit request =>
    User(id) match {
      case Some(user) =>
        userForm(user).bindFromRequest.fold(
          errors => BadRequest(view.edit(errors, routes.Users.update(id), "Update", "User edit")), {
          user =>
            transaction { user.save }
            Redirect(routes.Users.index)
        })
      case _ => NotFound
    }
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
