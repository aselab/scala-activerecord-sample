package controllers

import com.github.aselab.activerecord._
import models._

trait UserController extends ApplicationController {
  before("/user*") {
    contentType = "text/html"
  }

  def getId = params.getAs[Long]("id").getOrElse(halt(400))

  def userFormValues = (
    params.get("name"),
    params.getAs[Int]("age"),
    params.get("description")
  ) match  {
    case (Some(name), Some(age), description) => (name, age, description)
    case _ => halt(400)
  }

  get("/user") {
    layoutTemplate("/WEB-INF/views/user/index.ssp",
      "title" -> "User list", "users" -> User.all.toList)
  }

  post("/user") {
    userFormValues match {
      case (name, age, description) =>
        val user = User(name, age, description)
        user.save
        redirect("/user/" + user.id)
    }
  }

  get("/user/:id") {
    val id = getId
    User(id) match {
      case Some(user) => layoutTemplate("/WEB-INF/views/user/show.ssp",
        "title" -> "User info", "user" -> user)
      case None => halt(404)
    }
  }

  get("/user/new") {
    layoutTemplate("/WEB-INF/views/user/edit.ssp",
      "title" -> "User create", "user" -> new User, "action" -> "/user", "buttonLabel" -> "Create")
  }


  get("/user/:id/edit") {
    val id = getId
    User(id) match {
      case Some(user) => layoutTemplate("/WEB-INF/views/user/edit.ssp",
        "title" -> "User edit", "user" -> user, "action" -> "/user/%s".format(id), "buttonLabel" -> "Save")
      case None => halt(404)
    }
  }

  post("/user/:id") {
    val id = getId
    (User(id), userFormValues) match {
      case (Some(user), (name, age, description)) =>
        user.name = name
        user.age = age
        user.description = description
        user.save
        redirect("/user/" + id)

      case (None, _) => halt(404)
      case _ => halt(400)
    }
  }

  delete("/user/:id") {
    val id = getId
    User(id) match {
      case Some(user) => user.delete
      case None => halt(404)
    }
    redirect("/user")
  }

}

