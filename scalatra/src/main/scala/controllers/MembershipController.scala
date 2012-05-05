package controllers

import org.scalatra.ScalatraServlet
import net.liftweb.json._
import models._

class MembershipController extends ScalatraServlet with ApplicationController {
  before("*") {
    contentType = "text/html"
  }

  get("/") {
    layoutTemplate("/WEB-INF/views/membership/index.ssp",
      "projects" -> Project.all.toList,
      "users" -> User.all.toList,
      "roles" -> Role.all.toList,
      "memberships" -> Membership.all.toList
    )
  }

  post("/") {
    Membership.deleteAll
    val json = parse(request.body)
    json.children.foreach(o => Membership(o).save)

    contentType = "application/json"
    "{}"
  }
}
