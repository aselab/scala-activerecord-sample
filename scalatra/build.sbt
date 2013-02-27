name := "scala-activerecord-scalatra-sample"

organization := "com.github.aselab"

version := "0.2-SNAPSHOT"

scalaVersion := "2.10.0"

webSettings

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

classpathTypes ~= (_ + "orbit")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % "0.2-SNAPSHOT",
  "com.h2database" % "h2" % "1.3.170",
  "ch.qos.logback" % "logback-classic" % "1.0.9",
  "org.scalatra" %% "scalatra" % "2.2.0",
  "org.scalatra" %% "scalatra-scalate" % "2.2.0",
  "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % 
    "container" artifacts Artifact("javax.servlet", "jar", "jar"),
  "net.liftweb" %% "lift-json" % "2.5-M4"
)

