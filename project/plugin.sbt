libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v+"-0.2.11.1"))

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies += Defaults.sbtPluginExtra("play" % "sbt-plugin" % "2.1-09092012", "0.12", "2.9.2")
