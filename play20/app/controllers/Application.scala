package controllers

import play.api._
import play.api.mvc._

import models._
import com.github.aselab.activerecord.dsl._

object Application extends Controller {

  def index = Action { transaction {
    Ok(views.html.index(User.all.toList.toString))
  }}

}
