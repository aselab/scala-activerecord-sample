lazy val _version = "0.6.2"

name := "scalatra-multiple-schema-sample"

organization := "com.github.aselab"

version := _version

scalaVersion := "2.13.3"

val ScalatraVersion = "2.7.0"

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
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
)

containerLibs in Jetty := Seq(
  "org.eclipse.jetty" % "jetty-runner" % "9.4.31.v20200723" intransitive()
)

javaOptions in Jetty += "-Xmx2g"
