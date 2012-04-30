package controllers

import org.scalatra.scalate.ScalateSupport
import com.github.aselab.activerecord._
import models._

trait UserController extends ScalateSupport with ScalatraDatabaseSupport {
  private def getLong(key: String) =
    params.get(key).map(s => try { s.toLong } catch { case e => pass() }).getOrElse(halt(400))

  // TODO: Requires validation and deserialization. It's so lazy.
  private def data = Map(
    "name" -> params.getOrElse("name", ""),
    "age" -> params.getOrElse("age", 0).toString.toInt,
    "description" -> params.getOrElse("description", "")
  ).toSeq

  before("/user*") {
    contentType = "text/html"
  }

  get("/user") {
    layoutTemplate("templates/user/index.ssp",
      "title" -> "User list", "users" -> User.all.toList)
  }

  get("/user/new") {
    layoutTemplate("templates/user/edit.ssp",
      "title" -> "User create", "user" -> User("", 0, ""), "action" -> "/user", "buttonLabel" -> "Create")
  }

  post("/user") {
    val user = User("", 0, "")
    user(data: _*).save
    redirect("/user")
  }

  get("/user/:id") {
    layoutTemplate("templates/user/show.ssp",
      "title" -> "User info", "user" -> User(getLong("id")).get)
  }

  get("/user/:id/edit") {
    val id = getLong("id")
    layoutTemplate("templates/user/edit.ssp",
      "title" -> "User edit", "user" -> User(id).get, "action" -> "/user/%d".format(id), "buttonLabel" -> "Save")
  }

  post("/user/:id") {
    val id = getLong("id")
    val user = User(id).get
    user(data: _*).save
    redirect("/user/" + id)
  }

  delete("/user/:id") {
    val id = getLong("id")
    val user = User(id).get
    user.delete
    redirect("/user")
  }

}

