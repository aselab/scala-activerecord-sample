lazy val _version = "0.4.0"

name := "scalatra-multiple-schema-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.12.3"

enablePlugins(JettyPlugin)

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-scalatra" % _version,
  "com.h2database" % "h2" % "1.4.196",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatra" %% "scalatra" % "2.5.1",
  "org.scalatra" %% "scalatra-scalate" % "2.5.1",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
)

addCommandAlias("scalatraMultipleSchemaRun", "scalatraMultipleSchemaSample/jetty:start")

javaOptions in Jetty += "-Xmx2g"
