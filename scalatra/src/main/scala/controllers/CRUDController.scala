package controllers

import org.scalatra.ScalatraServlet
import com.github.aselab.activerecord._
import mojolly.inflector.InflectorImports._

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

  // ToDo: Add support for binding from Map[String, String] to ActiveRecord
  def form(implicit m: T = companion.newInstance) = new {
    def bind = {
      import ReflectionUtil._
      companion.fieldInfo.withFilter {
        case (name, info) =>
          !classOf[RecordRelation].isAssignableFrom(info.fieldType)
      }.foreach {
        case (name, info) =>
          val value = params.get(name).map {s =>
            try {
              info.fieldType.getSimpleName match {
                case "String" => s
                case "Integer" => s.toInt
              }
            } catch {
              case e => halt(400)
            }
          }
          if (info.isOption) m.setValue(name, value)
          else m.setValue(name, value.getOrElse(halt(400)))
      }
      m
    }
  }

  def index = layoutTemplate(viewsRoot + "index.ssp",
    "title" -> ("Listing " + pluralName),
    pluralName.underscore.camelize -> companion.all.toList
  )

  def create =  {
    val m = form.bind
    m.save
    if (!withoutShow) redirect(root + m.id)
    else redirect(root)
  }

  def show(id: Long) = companion(id) match {
    case Some(m) => layoutTemplate(viewsRoot + "show.ssp",
      "title" -> ("Show " + modelName),
      modelName.underscore.camelize -> m
    )
    case None => halt(404)
  }

  def add = layoutTemplate(viewsRoot + "edit.ssp",
    "title" -> ("Create " + modelName),
    modelName.underscore.camelize -> companion.newInstance,
    "action" -> root, "buttonLabel" -> "Create"
  )

  def edit(id: Long) = companion(id) match {
    case Some(m) => layoutTemplate(viewsRoot + "edit.ssp",
      "title" -> ("Update " + modelName),
      modelName.underscore.camelize -> m,
      "action" -> (root + id), "buttonLabel" -> "Save"
    )
    case None => halt(404)
  }

  def update(id: Long) = companion(id) match {
    case Some(model) =>
      val m = form(model).bind
      m.save
      if (!withoutShow) redirect(root + id)
      else redirect(root)

    case None => halt(404)
  }

  def destroy(id: Long) = companion(id) match {
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
