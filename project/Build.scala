import sbt._
import Keys._
import play.twirl.sbt.Import._

object ScalaActiveRecordSample extends Build {
  lazy val sample = project

  lazy val scalatra = project

  lazy val scalatraMultipleSchemaSample = project in file("scalatra-multiple-schema")

  lazy val play2x = (project in file("play2x")).enablePlugins(play.sbt.PlayScala).settings(
    TwirlKeys.templateImports += "com.github.aselab.activerecord.views.dsl._"
  )

  lazy val play2xMultipleSchemaSample = (project in file("play2x-multiple-schema")).enablePlugins(play.sbt.PlayScala).settings(
    TwirlKeys.templateImports += "com.github.aselab.activerecord.views.dsl._"
  )

  override def rootProject = Some(play2x)
}
