import sbt._
import Keys._
import play.Project._

object ScalaActiveRecordSample extends Build {
  lazy val sample = Project("sample", file("sample/"))

  lazy val scalatra = Project("scalatra", file("scalatra/"))

  lazy val appDependencies = Seq(
    "com.github.aselab" %% "scala-activerecord" % "0.2.2",
    "com.github.aselab" %% "scala-activerecord-play2" % "0.2.2",
    jdbc,
    "com.h2database" % "h2" % "1.3.172"
  )

  lazy val play2x = play.Project(
    "play2x-sample",
    dependencies = appDependencies,
    path = file("play2x/")
  ).settings(
    scalaVersion := "2.10.2",
    resolvers += Resolver.sonatypeRepo("releases"),
    templatesImport += "com.github.aselab.activerecord.views.dsl._"
  )
}
