val _version = "0.3.1-SNAPSHOT"

name := "scala-activerecord-play2x-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.11.5"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-play2" % _version,
  "com.github.aselab" %% "scala-activerecord-play2-specs" % _version % "test",
  jdbc,
  "com.h2database" % "h2" % "1.4.185",
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "bootstrap" % "3.3.2-1",
  "org.webjars" % "bootstrap-datepicker" % "1.3.1",
  "org.webjars" % "bootstrap-datetimepicker" % "2.3.1"
)

activerecordPlaySettings
