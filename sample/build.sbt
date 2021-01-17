val _version = "0.6.2"

name := "scala-activerecord-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.13.4"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.h2database" % "h2" % "1.4.200",
  "org.slf4j" % "slf4j-nop" % "1.7.25"
  // for debug
  //"org.slf4j" % "slf4j-api" % "1.7.25",
  //"ch.qos.logback" % "logback-classic" % "1.2.3"
)

scalacOptions ++= Seq("-deprecation", "-unchecked")

discoveredMainClasses in Compile ~= {_.sorted}
