name := "scala-activerecord-sample"

organization := "com.github.aselab"

version := "0.1"

resolvers ++= Seq(
  "aselab repo" at "http://aselab.github.com/maven/",
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "com.github.aselab" % "scala-activerecord" % "0.1",
  "com.h2database" % "h2" % "1.3.157",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)

scalacOptions ++= Seq("-deprecation", "-unchecked")
