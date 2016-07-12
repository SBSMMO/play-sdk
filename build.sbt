//import org.scoverage.coveralls.Imports.CoverallsKeys._

name := """play-sdk"""

version in ThisBuild := "0.1"
scalaVersion in ThisBuild := "2.11.8"

EclipseKeys.withSource := true

libraryDependencies ++= Seq(
  //"com.typesafe.akka" %% "akka-actor" % "2.3.11",
  //"com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)