ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.7"

lazy val root = (project in file("."))
  .settings(
    name := "web-server"
  )

val akkaVersion = "2.6.18"
val akkaHttpVersion = "10.2.7"
val commonsIoVersion = "2.11.0"
val logbackVersion = "1.2.10"
val guiceVersion = "5.0.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream"      % akkaVersion,
  "com.typesafe.akka" %% "akka-http"        % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-slf4j"       % akkaVersion,
  "commons-io"         % "commons-io"       % commonsIoVersion,
  "ch.qos.logback"     % "logback-classic"  % logbackVersion % Runtime,
  "com.google.inject"  % "guice"            % guiceVersion
)