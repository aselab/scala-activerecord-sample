import sbt._
import Keys._

object ScalaActiveRecordSample extends Build {
  lazy val simple = Project("simple", file("simple/"))
  lazy val scalatra = Project("scalatra", file("scalatra/"))
}
