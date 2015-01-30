package controllers

import org.scalatra.ScalatraServlet

class MainController extends ScalatraServlet with ApplicationController {
  get("/") {
    contentType = "text/html"
    layoutTemplate("/WEB-INF/views/index.ssp",
      "title" -> "Index page", "message" -> "Hello, Scala ActiveRecord")
  }
}

