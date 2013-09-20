name := "scala-activerecord-sample"

organization := "com.github.aselab"

version := "0.2.3"

scalaVersion := "2.10.2"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % "0.2.3-SNAPSHOT",
  "com.h2database" % "h2" % "1.3.172",
  "org.slf4j" % "slf4j-nop" % "1.7.5"
)

scalacOptions ++= Seq("-deprecation", "-unchecked")

discoveredMainClasses in Compile ~= {_.sorted}
