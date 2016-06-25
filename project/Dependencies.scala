import sbt._

object Dependencies {

  val resolvers = Seq(
    "Atlassian Releases" at "https://maven.atlassian.com/public/",
    "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
  )
  
  object Library {

    object Play {
      val version = "2.5.4"
      val ws = "com.typesafe.play" %% "play-ws" % version
      val cache = "com.typesafe.play" %% "play-cache" % version
      val test = "com.typesafe.play" %% "play-test" % version
      val specs2 = "com.typesafe.play" %% "play-specs2" % version
      object Specs2 {
        private val version = "3.8.3"
        val matcherExtra = "org.specs2" %% "specs2-matcher-extra" % version
        val mock = "org.specs2" %% "specs2-mock" % version
      }
    }
    
    object Silhouette {
      val version = "4.0.0-RC1"
      val silhouette = "com.mohiva" %% "play-silhouette" % version
      val silhouetteBcrypt = "com.mohiva" %% "play-silhouette-password-bcrypt" % version
      val silhouettePersistence = "com.mohiva" %% "play-silhouette-persistence" % version
      val silhouetteCryptoJCA = "com.mohiva" %% "play-silhouette-crypto-jca" % version
      val silhouetteCAS = "com.mohiva" %% "play-silhouette-cas" % version
    }

    object Specs2 {
      private val version = "3.8.3"
      val core = "org.specs2" %% "specs2-core" % version
      val matcherExtra = "org.specs2" %% "specs2-matcher-extra" % version
      val mock = "org.specs2" %% "specs2-mock_2" % version
    }
    
    val scalaGuice = "net.codingwell" %% "scala-guice" % "4.0.1"
    val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % "2.4.2"
    
  }

}