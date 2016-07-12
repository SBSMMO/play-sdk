import sbt.Keys._
import sbt._

object Build extends Build {
  
  //required for the javaOptions to be passed in
  fork := true

  javaOptions in (Test) += "-Xdebug"

  javaOptions in (Test) += "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
  
  lazy val common = Project(
    id = "play-sdk-common",
    base = file("common")
  )
  
  lazy val play = Project(
    id = "play-sdk-play",
    base = file("play"),
    dependencies = Seq(common)
  )
  
  lazy val persistence = Project(
    id = "play-sdk-persistence",
    base = file("persistence"),
    dependencies = Seq(common)
  )

  lazy val security = Project(
    id = "play-sdk-security",
    base = file("security"),
    dependencies = Seq(common, persistence, play)
  )
  
  val root = Project(
    id = "root",
    base = file("."),
    aggregate = Seq(
      common,
      persistence,
      play,
      security
    ),
    settings = Defaults.coreDefaultSettings)
  
  
}