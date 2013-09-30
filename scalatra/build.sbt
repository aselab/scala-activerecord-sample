val _version = "0.2.3-SNAPSHOT"

name := "scala-activerecord-scalatra-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.10.2"

webSettings

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-scalatra" % _version,
  "com.h2database" % "h2" % "1.3.172",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "org.scalatra" %% "scalatra" % "2.2.0",
  "org.scalatra" %% "scalatra-scalate" % "2.2.0",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.13.v20130916" % "container",
  "net.liftweb" %% "lift-json" % "2.5.1"
)

