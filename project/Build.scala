import sbt._
import Keys._
import play.twirl.sbt.Import._

object ScalaActiveRecordSample extends Build {
  lazy val sample = project

  lazy val scalatra = project

  lazy val play2x = (project in file("play2x")).enablePlugins(play.PlayScala).settings(
    TwirlKeys.templateImports += "com.github.aselab.activerecord.views.dsl._"
  )

  override def rootProject = Some(play2x)
}
