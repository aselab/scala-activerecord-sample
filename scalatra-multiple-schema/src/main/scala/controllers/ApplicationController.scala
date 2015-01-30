package controllers

import org.scalatra._
import org.scalatra.i18n._
import org.scalatra.scalate.ScalateSupport
import com.github.aselab.activerecord.scalatra._

trait ApplicationController extends ScalatraKernel with ScalateSupport
  with I18nSupport with CookieSupport
