name := "scala-activerecord-sample"

organization := "com.github.aselab"

version := "0.1"

resolvers ++= Seq(
  "aselab repo" at "http://aselab.github.com/maven/",
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "com.github.aselab" % "scala-activerecord" % "0.1-SNAPSHOT"
)

scalacOptions ++= Seq("-deprecation", "-unchecked")
