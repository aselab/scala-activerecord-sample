import play.Project._

val _version = "0.2.3"

name := "scala-activerecord-play2x-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.10.2"

playScalaSettings

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-play2" % _version,
  jdbc,
  "com.h2database" % "h2" % "1.3.172"
)

templatesImport += "com.github.aselab.activerecord.views.dsl._"
