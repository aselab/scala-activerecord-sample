resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.sonatypeRepo("snapshots")
)

addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "3.0.3")

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.2")

addSbtPlugin("com.github.aselab" % "scala-activerecord-play2-sbt" % "0.4.0-SNAPSHOT")

addSbtPlugin("com.github.aselab" % "scala-activerecord-scalatra-sbt" % "0.4.0-SNAPSHOT")
