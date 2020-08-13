val _version = "0.6.0"

name := "scala-activerecord-play2x-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.13.3"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-play2" % _version,
  "com.github.aselab" %% "scala-activerecord-play2-specs" % _version % "test",
  guice,
  filters,
  jdbc,
  evolutions,
  specs2 % Test,
  "com.h2database" % "h2" % "1.4.200",
  "org.webjars" %% "webjars-play" % "2.8.0-1",
  "org.webjars" % "bootstrap" % "3.3.7-1",
  "org.webjars" % "bootstrap-datepicker" % "1.9.0",
  "org.webjars" % "bootstrap-datetimepicker" % "2.4.4"
)

routesGenerator := InjectedRoutesGenerator

scalacOptions ++= Seq(
  "-Ywarn-unused",
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Xlint"
)

lazy val root = (project in file(".")).enablePlugins(play.sbt.PlayScala).settings(
  TwirlKeys.templateImports += "com.github.aselab.activerecord.views.dsl._"
)
