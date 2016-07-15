val _version = "0.4.0-SNAPSHOT"

name := "play2x-multiple-schema-sample"

organization := "com.github.aselab"

scalaVersion := "2.11.8"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-play2" % _version,
  jdbc,
  evolutions,
  "com.h2database" % "h2" % "1.4.192",
  "org.webjars" %% "webjars-play" % "2.5.0-2",
  "org.webjars" % "bootstrap" % "3.3.2-1"
)

activerecordPlaySettings
