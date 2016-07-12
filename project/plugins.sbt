resolvers += Resolver.typesafeRepo("releases")

resolvers += Classpaths.sbtPluginReleases

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.3.5")

addSbtPlugin("com.typesafe.sbteclipse" %% "sbteclipse-plugin" % "4.0.0")

addSbtPlugin("com.typesafe.play" %% "sbt-plugin" % "2.5.4")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")