package controllers

import org.scalatra.scalate.ScalateSupport
import com.github.aselab.activerecord._
import models._

trait UserController extends ScalateSupport with ScalatraDatabaseSupport {
  private def getLong(key: String) =
    params.get(key).map(s => try { s.toLong } catch { case e => pass() }).getOrElse(halt(400))

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
    val name = params.getOrElse("name", halt(400))
    val age = params.get("age").map(_.toInt).getOrElse(halt(400))
    val description = params.getOrElse("description", "")
    User(name, age, description).save
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
    User(id).foreach { user =>
      user.name = params.getOrElse("name", halt(400))
      user.age = params.get("age").map(_.toInt).getOrElse(halt(400))
      user.description = params.getOrElse("description", "")
      user.save
      redirect("/user/" + id)
    }
    halt(400)
  }

  delete("/user/:id") {
    val id = getLong("id")
    val user = User(id).get
    user.delete
    redirect("/user")
  }

}

