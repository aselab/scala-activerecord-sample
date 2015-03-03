lazy val _version = "0.3.1"

name := "scala-activerecord-scalatra-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.11.6"

jetty()

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-scalatra" % _version,
  "com.h2database" % "h2" % "1.4.185",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.scalatra" %% "scalatra" % "2.3.0",
  "org.scalatra" %% "scalatra-scalate" % "2.3.0",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "9.3.0.M1" % "container",
  "org.eclipse.jetty" % "jetty-plus" % "9.3.0.M1" % "container"
)

addCommandAlias("scalatraRun", "scalatra/container:start")

javaOptions in container += "-Xmx2g"

activerecordScalatraSettings
