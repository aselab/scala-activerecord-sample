import sbt._
import Keys._
import PlayProject._

object ScalaActiveRecordSample extends Build {
  lazy val simple = Project("simple", file("simple/"))

  lazy val scalatra = Project("scalatra", file("scalatra/"))

  lazy val appDependencies = Seq(
    "com.github.aselab" % "scala-activerecord" % "0.2-SNAPSHOT",
    "com.h2database" % "h2" % "1.3.170"
  )

  lazy val play20 = PlayProject(
    "play20-sample",
    dependencies = appDependencies,
    mainLang = SCALA,
    path = file("play20/")
  ).settings(
    resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  )
}
