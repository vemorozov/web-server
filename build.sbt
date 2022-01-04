ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.7"

lazy val root = (project in file("."))
  .settings(
    name := "web-server"
  )

val AkkaVersion = "2.6.18"
val AkkaHttpVersion = "10.2.7"
val CommonsIoVersion = "2.11.0"
val LogbackVersion = "1.2.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream"      % AkkaVersion,
  "com.typesafe.akka" %% "akka-http"        % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-slf4j"       % AkkaVersion,
  "commons-io"         % "commons-io"       % CommonsIoVersion,
  "ch.qos.logback"     % "logback-classic"  % LogbackVersion % Runtime
)