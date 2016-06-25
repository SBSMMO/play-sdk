import Dependencies._

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(  
  Library.scalaGuice,
  Library.Play.test,
  Library.Play.specs2 % Test,
  Library.Play.Specs2.matcherExtra % Test,
  Library.Play.Specs2.mock % Test,
//Library.scalaGuice % Test,
  Library.akkaTestkit % Test
)