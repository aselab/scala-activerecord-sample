package controllers

import org.scalatra._
import org.scalatra.i18n._
import org.scalatra.scalate.ScalateSupport

trait ApplicationController extends ScalatraKernel with ScalateSupport
  with ScalatraDatabaseSupport with I18nSupport with CookieSupport

