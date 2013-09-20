name := "scala-activerecord-scalatra-sample"

organization := "com.github.aselab"

version := "0.2.3"

scalaVersion := "2.10.2"

webSettings

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Classpaths.typesafeReleases
)

classpathTypes ~= (_ + "orbit")

libraryDependencies ++= Seq(
  "com.github.aselab" %% "scala-activerecord" % "0.2.3-SNAPSHOT",
  "com.github.aselab" %% "scala-activerecord-scalatra" % "0.2.3-SNAPSHOT",
  "com.h2database" % "h2" % "1.3.172",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "org.scalatra" %% "scalatra" % "2.2.0",
  "org.scalatra" %% "scalatra-scalate" % "2.2.0",
  "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.10.v20130312" % "container",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % 
    "container" artifacts Artifact("javax.servlet", "jar", "jar"),
  "net.liftweb" %% "lift-json" % "2.5-RC2"
)

