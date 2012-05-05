name := "scala-activerecord-scalatra-sample"

organization := "com.github.aselab"

version := "0.1"

scalaVersion := "2.9.1"

seq(webSettings :_*)

resolvers ++= Seq(
  "aselab repo" at "http://aselab.github.com/maven/",
  "typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "com.github.aselab" % "scala-activerecord" % "0.1-SNAPSHOT",
  "com.h2database" % "h2" % "1.3.157",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.scalatra" %% "scalatra" % "2.0.4",
  "org.scalatra" %% "scalatra-scalate" % "2.0.4",
  "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.0.v20120127" % "container",
  "net.liftweb" %% "lift-json" % "2.4"
)

