package controllers

import org.scalatra.ScalatraServlet
import net.liftweb.json._
import models._

class MembershipController extends ScalatraServlet with ApplicationController {
  before("*") {
    contentType = "text/html"
  }

  get("/") {
    views.membership.html.index(
      Project.all.toList,
      User.all.toList,
      Role.all.toList,
      Membership.all.toList
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
