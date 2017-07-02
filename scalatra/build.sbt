lazy val _version = "0.4.0-SNAPSHOT"

name := "scala-activerecord-scalatra-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.12.2"

enablePlugins(JettyPlugin)

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-scalatra" % _version,
  "com.h2database" % "h2" % "1.4.196",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatra" %% "scalatra" % "2.5.0",
  "org.scalatra" %% "scalatra-scalate" % "2.5.1",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
)

addCommandAlias("scalatraRun", "scalatra/jetty:start")

javaOptions in Jetty += "-Xmx2g"

activerecordScalatraSettings

scalacOptions ++= Seq(
  "target:jvm-1.8",
  "-Ywarn-unused",
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Ywarn-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Xlint"
)
