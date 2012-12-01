package controllers

import org.scalatra.ScalatraServlet
import com.github.aselab.activerecord._
import mojolly.inflector.InflectorImports._
import java.util.Locale
import twirl.api._

abstract class CRUDController[T <: ActiveRecord](
  withoutIndex: Boolean = false,
  withoutCreate: Boolean = false,
  withoutShow: Boolean = false,
  withoutUpdate: Boolean = false,
  withoutDestroy: Boolean = false
)(implicit m: Manifest[T]) extends ScalatraServlet with ApplicationController {

  val companion = ReflectionUtil.classToCompanion(m.erasure)
    .asInstanceOf[ActiveRecordCompanion[T]]
  import companion._

  val modelName = m.erasure.getSimpleName
  val pluralName = modelName.pluralize

  val root = "/" + modelName.underscore + "/"

  before("/*") {
    contentType = "text/html"
  }

  def template[A](viewName: String) = {
    val classLoader = getClass.getClassLoader
    val viewClassName = "views.%s.html.%s$".format(pluralName.underscore.camelize, viewName)
    val viewObject = classLoader.loadClass(viewClassName).getField("MODULE$").get(null)
    viewObject.asInstanceOf[A]
  }

  def template1[A](viewName: String) = template[Template1[A, Html]](viewName)

  def getId = params.getAs[Long]("id").getOrElse(halt(400))

  def index = template1[List[T]]("index").render(companion.all.toList)

  def create =  {
    val m = companion.bind(params)
    if (m.errors.isEmpty && m.saveWithoutValidation) {
      if (!withoutShow) redirect(root + m.id)
      else redirect(root)
    } else {
      renderForm(m)
    }
  }

  def show(id: Long) = companion(id) match {
    case Some(m) => template1[T]("show").render(m)
    case None => halt(404)
  }

  def renderForm(m: T) = {
    val title = (if (m.isNewRecord) "Create " else "Update ") + modelName
    val action = root + (if (m.isNewRecord) "" else m.id)
    val buttonLabel = if (m.isNewRecord) "Create" else "Save"
    val formTemplate = template[Template5[T, String, String, String, Locale, Html]]("edit")
    formTemplate.render(m, action, buttonLabel, title, locale)
  }

  def add = renderForm(companion.newInstance)

  def edit(id: Long) = companion(id) match {
    case Some(m) => renderForm(m)
    case None => halt(404)
  }

  def update(id: Long) = companion(id) match {
    case Some(model) =>
      val m = companion.bind(params)(model)
      if (m.errors.isEmpty && m.saveWithoutValidation) {
        if (!withoutShow) redirect(root + id)
        else redirect(root)
      } else {
        renderForm(m)
      }

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
