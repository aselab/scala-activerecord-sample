package controllers

import org.scalatra.ScalatraServlet

class MainController extends ScalatraServlet with ApplicationController {
  get("/") {
    views.html.index("Hello, Scala ActiveRecord")
  }
}

