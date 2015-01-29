package controllers

import com.github.aselab.activerecord.dsl._
import org.scalatra.ScalatraServlet
import models._

class MembershipController extends ScalatraServlet with ApplicationController {
  before("*") {
    contentType = "text/html"
  }

  get("/") {
    layoutTemplate("/WEB-INF/views/membership/index.ssp",
      "projects" -> Project.all.toList,
      "users" -> User.all.toList,
      "roles" -> Role.all.toList
    )
  }

  post("/") {
    Membership.deleteAll
    Membership.fromArrayJson(request.body).foreach(_.save)

    contentType = "application/json"
    "{}"
  }
}
