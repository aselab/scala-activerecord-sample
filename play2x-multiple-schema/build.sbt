val _version = "0.4.0-SNAPSHOT"

name := "play2x-multiple-schema-sample"

organization := "com.github.aselab"

scalaVersion := "2.12.1"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-play2" % _version,
  guice,
  jdbc,
  evolutions,
  "com.h2database" % "h2" % "1.4.193",
  "org.webjars" %% "webjars-play" % "2.6.0-M1",
  "org.webjars" % "bootstrap" % "3.3.7-1"
)

activerecordPlaySettings
