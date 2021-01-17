lazy val _version = "0.6.2"

name := "scalatra-multiple-schema-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.13.4"

val ScalatraVersion = "2.7.1"

enablePlugins(ScalatraPlugin)
enablePlugins(JettyPlugin)

containerPort in Jetty := 8080

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % _version,
  "com.github.aselab" %% "scala-activerecord-scalatra" % _version,
  "com.h2database" % "h2" % "1.4.200",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
  "org.scalatra.scalate" %% "scalate-core" % "1.9.6",
  "javax.servlet" % "javax.servlet-api" % "4.0.1" % "provided"
)

javaOptions in Jetty += "-Xmx2g"
