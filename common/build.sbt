import Dependencies._

libraryDependencies ++= Seq(  
  Library.scalaGuice,
  Library.scalaTest,
  Library.Play.play,
  //Library.Play.test,
  //Library.Play.specs2 % Test,
  //Library.Play.Specs2.matcherExtra % Test,
  //Library.Play.Specs2.mock % Test,
//Library.scalaGuice % Test,
  Library.akkaTestkit % Test
)