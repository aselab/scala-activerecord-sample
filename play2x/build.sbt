import play.Project._

val _version = "0.2.4-SNAPSHOT"

name := "scala-activerecord-play2x-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.10.3"

playScalaSettings

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-play2" % _version,
  jdbc,
  "com.h2database" % "h2" % "1.3.174",
  "org.webjars" %% "webjars-play" % "2.2.1",
  "org.webjars" % "bootstrap" % "3.0.0"
)

templatesImport += "com.github.aselab.activerecord.views.dsl._"
