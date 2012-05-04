package controllers

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
}
