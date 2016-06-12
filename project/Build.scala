import sbt.Keys._
import sbt._

object Build extends Build {
  
  lazy val common = Project(
    id = "play-sdk-common",
    base = file("common")
  )

  lazy val security = Project(
    id = "play-sdk-security",
    base = file("security"),
    dependencies = Seq(common % "compile->compile;test->test")
  )
  
  val root = Project(
    id = "root",
    base = file("."),
    aggregate = Seq(
      common,
      security
    ),
    settings = Defaults.coreDefaultSettings)
  
}