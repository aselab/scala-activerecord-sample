lazy val _version = "0.3.2"

name := "scalatra-multiple-schema-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.11.8"

enablePlugins(JettyPlugin)

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-scalatra" % _version,
  "com.h2database" % "h2" % "1.4.192",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "org.scalatra" %% "scalatra" % "2.4.0",
  "org.scalatra" %% "scalatra-scalate" % "2.4.0",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.0.M0" % "container",
  "org.eclipse.jetty" % "jetty-plus" % "9.4.0.M0" % "container"
)

addCommandAlias("scalatraMultipleSchemaRun", "scalatraMultipleSchemaSample/jetty:start")

javaOptions in Jetty += "-Xmx2g"

activerecordScalatraSettings
