import sbt._
import Keys._

object ScalaActiveRecordSample extends Build {
  lazy val sample = project

  lazy val scalatra = project

  lazy val play2x = project

  override def rootProject = Some(play2x)
}
