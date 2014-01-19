val _version = "0.2.3"

name := "scala-activerecord-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.10.2"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.h2database" % "h2" % "1.3.172",
  "org.slf4j" % "slf4j-nop" % "1.7.5"
)

scalacOptions ++= Seq("-deprecation", "-unchecked")

discoveredMainClasses in Compile ~= {_.sorted}

activerecordGeneratorSettings
