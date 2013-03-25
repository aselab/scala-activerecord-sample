name := "scala-activerecord-sample"

organization := "com.github.aselab"

version := "0.2.2"

scalaVersion := "2.10.1"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % "0.2.2",
  "com.h2database" % "h2" % "1.3.170",
  "org.slf4j" % "slf4j-nop" % "1.7.3"
)

scalacOptions ++= Seq("-deprecation", "-unchecked")

discoveredMainClasses in Compile ~= {_.sorted}
