import sbt._
import Keys._
import PlayProject._

object ScalaActiveRecordSample extends Build {
  lazy val simple = Project("simple", file("simple/"))

  lazy val scalatra = Project("scalatra", file("scalatra/"))

  lazy val appDependencies = Seq(
    "com.github.aselab" % "scala-activerecord" % "0.1",
    "com.h2database" % "h2" % "1.3.165",
    "org.slf4j" % "slf4j-nop" % "1.6.4"
  )

  lazy val play20 = PlayProject(
    "play20-sample",
    dependencies = appDependencies,
    mainLang = SCALA,
    path = file("play20/")
  ).settings(
    resolvers ++= List("aselab repo" at "http://aselab.github.com/maven/")
  )
}
