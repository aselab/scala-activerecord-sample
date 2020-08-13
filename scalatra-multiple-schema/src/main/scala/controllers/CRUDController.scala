package controllers

import org.scalatra.ScalatraServlet
import com.github.aselab.activerecord._
import reflections._
import com.github.aselab.activerecord.dsl._
import com.github.aselab.activerecord.util.InflectorImports._

abstract class CRUDController[T <: ActiveRecord](
  withoutIndex: Boolean = false,
  withoutCreate: Boolean = false,
  withoutShow: Boolean = false,
  withoutUpdate: Boolean = false,
  withoutDestroy: Boolean = false
)(implicit m: Manifest[T]) extends ScalatraServlet with ApplicationController {
  val companion = ReflectionUtil.classToCompanion(m.erasure)
    .asInstanceOf[ActiveRecordCompanion[T]]

  val modelName = m.erasure.getSimpleName
  val pluralName = modelName.pluralize

  val root = "/" + modelName.underscore + "/"
  val viewsRoot = "/WEB-INF/views" + root

  before("/*") {
    contentType = "text/html"
  }

  def getId = params.getAs[Long]("id").getOrElse(halt(400))

  def index = layoutTemplate(viewsRoot + "index.ssp",
    "title" -> ("Listing " + pluralName),
    pluralName.underscore.camelize -> companion.all.toList
  )

  def create =  {
    val m = companion.bind(params.toMap)
    if (m.save) {
      if (!withoutShow) redirect(root + m.id)
      else redirect(root)
    } else {
      renderForm(m)
    }
  }

  def show(id: Long) = companion.find(id) match {
    case Some(m) => layoutTemplate(viewsRoot + "show.ssp",
      "title" -> ("Show " + modelName),
      modelName.underscore.camelize -> m
    )
    case None => halt(404)
  }

  def renderForm(m: T) = {
    val d = collection.mutable.Map[String, Any](
      modelName.underscore.camelize -> m,
      "locale" -> locale
    )
    if (m.isNewRecord) {
      d("title") = "Create " + modelName
      d("action") = root
      d("buttonLabel") = "Create"
    } else {
      d("title") = "Update " + modelName
      d("action") = root + m.id
      d("buttonLabel") = "Save"
    }

    layoutTemplate(viewsRoot + "edit.ssp", d.toSeq:_*)
  }

  def add = renderForm(companion.newInstance)

  def edit(id: Long) = companion.find(id) match {
    case Some(m) => renderForm(m)
    case None => halt(404)
  }

  def update(id: Long) = companion.find(id) match {
    case Some(model) =>
      val m = companion.bind(params.toMap)(model)
      if (m.save) {
        if (!withoutShow) redirect(root + id)
        else redirect(root)
      } else {
        renderForm(m)
      }

    case None => halt(404)
  }

  def destroy(id: Long) = companion.find(id) match {
    case Some(m) => m.delete
    case None => halt(404)
  }

  if (!withoutIndex) get("/") { index }

  if (!withoutShow) get("/:id") { show(getId) }

  if (!withoutCreate) {
    get("/new") { add }
    post("/") { create }
  }

  if (!withoutUpdate) {
    get("/:id/edit") { edit(getId) }
    post("/:id") { update(getId) }
  }

  if (!withoutDestroy) delete("/:id") { destroy(getId) }

}
