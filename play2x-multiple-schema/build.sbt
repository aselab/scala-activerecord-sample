val _version = "0.6.0"

name := "play2x-multiple-schema-sample"

organization := "com.github.aselab"

scalaVersion := "2.13.3"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-play2" % _version,
  guice,
  filters,
  jdbc,
  evolutions,
  "com.h2database" % "h2" % "1.4.200",
  "org.webjars" %% "webjars-play" % "2.8.0-1",
  "org.webjars" % "bootstrap" % "3.3.7-1"
)

lazy val root = (project in file(".")).enablePlugins(play.sbt.PlayScala).settings(
  TwirlKeys.templateImports += "com.github.aselab.activerecord.views.dsl._"
)
