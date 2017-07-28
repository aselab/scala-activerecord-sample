val _version = "0.4.0-SNAPSHOT"

name := "scala-activerecord-play2x-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.12.3"

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
  "com.h2database" % "h2" % "1.4.196",
  "org.webjars" %% "webjars-play" % "2.6.0",
  "org.webjars" % "bootstrap" % "3.3.7-1",
  "org.webjars" % "bootstrap-datepicker" % "1.6.4",
  "org.webjars" % "bootstrap-datetimepicker" % "2.4.2"
)

activerecordPlaySettings

routesGenerator := InjectedRoutesGenerator

scalacOptions ++= Seq(
  "target:jvm-1.8",
  "-Ywarn-unused",
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Ywarn-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Xlint"
)
