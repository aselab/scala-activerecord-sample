resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.sonatypeRepo("snapshots")
)

addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "0.6.0")

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.1")

addSbtPlugin("com.github.aselab" % "scala-activerecord-play2-sbt" % "0.2.4-SNAPSHOT")

addSbtPlugin("com.github.aselab" % "scala-activerecord-scalatra-sbt" % "0.2.4-SNAPSHOT")
