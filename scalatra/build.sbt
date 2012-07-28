name := "scala-activerecord-scalatra-sample"

organization := "com.github.aselab"

version := "0.2-SNAPSHOT"

scalaVersion := "2.9.2"

seq(webSettings :_*)

resolvers ++= Seq(
  "aselab repo" at "http://aselab.github.com/maven/",
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "com.github.aselab" % "scala-activerecord" % "0.2-SNAPSHOT",
  "com.h2database" % "h2" % "1.3.157",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.scalatra" % "scalatra_2.9.1" % "2.0.4",
  "org.scalatra" % "scalatra-scalate_2.9.1" % "2.0.4",
  "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.0.v20120127" % "container",
  "net.liftweb" % "lift-json_2.9.1" % "2.4"
)

