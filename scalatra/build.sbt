lazy val _version = "0.3.0"

name := "scala-activerecord-scalatra-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.11.4"

jetty()

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-scalatra" % _version,
  "com.h2database" % "h2" % "1.4.182",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.scalatra" %% "scalatra" % "2.3.0",
  "org.scalatra" %% "scalatra-scalate" % "2.3.0",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "9.2.5.v20141112" % "container",
  "org.eclipse.jetty" % "jetty-plus" % "9.2.5.v20141112" % "container",
  "net.liftweb" %% "lift-json" % "2.6-M4"
)

addCommandAlias("scalatraRun", "scalatra/container:start")

javaOptions in container += "-Xmx2g"

activerecordScalatraSettings
