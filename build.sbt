name := "akka-http-workshop-ind"

version := "0.1"

scalaVersion := "2.12.7"
val circeVersion = "0.10.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.18",
  "com.typesafe.akka" %% "akka-http" % "10.1.5",
  "com.typesafe.akka" %% "akka-stream" % "2.5.18",
  "com.softwaremill.sttp" %% "core" % "1.5.0",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "de.heikoseeberger" %% "akka-http-circe" % "1.22.0",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.18" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.5" % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.18" % Test
)

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

enablePlugins(JavaAppPackaging)

dockerBaseImage := "anapsix/alpine-java"
dockerExposedPorts ++= Seq(8000)