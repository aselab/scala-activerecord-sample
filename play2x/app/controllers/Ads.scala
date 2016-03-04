package controllers

import javax.inject.Inject

import play.api.mvc._
import play.api.i18n.{MessagesApi, I18nSupport}
import views.html.magazine.{ad => view}
import scala.concurrent.Future

import models._
import com.github.aselab.activerecord.dsl._

class Ads @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def withMagazine(magazineId: Long)(block: (Magazine, Request[AnyContent]) => Result) =
    Action { request =>
      Magazine.find(magazineId).map { m => block(m, request) }.getOrElse { NotFound }
    }

  def index(magazineId: Long) = withMagazine(magazineId) { (m, request) =>
    Ok(view.index(m.ads.toList, m.id))
  }

  def show(magazineId: Long, id: Long) = withMagazine(magazineId) { (m, request) =>
    m.ads.find(id) match {
      case Some(ad) => Ok(view.show(ad, magazineId))
      case _ => NotFound
    }
  }

  def newPage(magazineId: Long) = withMagazine(magazineId) { (m, request) =>
    Ok(view.edit(Ad.form, magazineId, routes.Ads.create(magazineId), "Create", "Ad create"))
  }

  def create(magazineId: Long) = withMagazine(magazineId) { (m, request) =>
    Ad.form(Ad.newInstance("magazineId" -> magazineId)).bindFromRequest()(request).fold(
      errors => BadRequest(view.edit(errors, magazineId, routes.Ads.create(magazineId), "Create", "Ad create")), {
      ad =>
        Ad.transaction { ad.save }
        Redirect(routes.Ads.show(ad.magazineId, ad.id))
    })
  }

  def edit(magazineId: Long, id: Long) = withMagazine(magazineId) { (m, request) =>
    m.ads.find(id) match {
      case Some(ad) =>
        Ok(view.edit(Ad.form(ad.assign("magazineId" -> magazineId)), magazineId, routes.Ads.update(magazineId, id), "Update", "Ad edit"))
      case _ => NotFound
    }
  }

  def update(magazineId: Long, id: Long) = withMagazine(magazineId) { (m, request) =>
    m.ads.find(id) match {
      case Some(ad) =>
        Ad.form(ad).bindFromRequest()(request).fold(
          errors => BadRequest(view.edit(errors, magazineId, routes.Ads.update(magazineId, id), "Update", "Ad edit")), {
          ad =>
            Ad.transaction { ad.save }
            Redirect(routes.Ads.index(magazineId))
        })
      case _ => NotFound
    }
  }

  def delete(magazineId: Long, id: Long) = withMagazine(magazineId) { (m, request) =>
    m.ads.find(id) match {
      case Some(ad) =>
        Ad.transaction { ad.delete }
        Ok
      case _ => NotFound
    }
  }
}
