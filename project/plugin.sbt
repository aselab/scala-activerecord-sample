libraryDependencies ++= Seq(
  "com.github.siasia" %% "xsbt-web-plugin" % "0.12.0-0.2.11.1",
  "com.github.scala-incubator.io" %% "scala-io-file"  % "0.4.1-seq"
)

resolvers ++= Seq(
  Classpaths.typesafeReleases, // typesafe-ivy-releases
  "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/"
)

addSbtPlugin("play" % "sbt-plugin" % "2.1-09142012")

addSbtPlugin("io.spray" % "sbt-twirl" % "0.6.1")
