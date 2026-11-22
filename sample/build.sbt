val _version = "0.7.0-SNAPSHOT"

name := "scala-activerecord-sample"

organization := "com.github.aselab"

version := _version

// scala-activerecord 0.7.0 supports Scala 3.7 (not 3.8 and later yet)
scalaVersion := "3.7.4"

// snapshot versions of scala-activerecord are published here
resolvers += "central-snapshots" at "https://central.sonatype.com/repository/maven-snapshots/"

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.h2database" % "h2" % "2.5.252",
  "org.slf4j" % "slf4j-nop" % "2.0.20"
  // for debug
  //"ch.qos.logback" % "logback-classic" % "1.6.7"
)

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

// Squeryl creates proxies of the models with cglib, which needs reflective access to java.lang
run / fork := true
run / javaOptions += "--add-opens=java.base/java.lang=ALL-UNNAMED"
run / outputStrategy := Some(StdoutOutput)

Compile / discoveredMainClasses ~= (_.sorted)
