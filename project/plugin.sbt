libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.11.1"))

resolvers ++= Seq(
  "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/"
)

addSbtPlugin("play" % "sbt-plugin" % "2.0.4")
