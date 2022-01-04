package com.dataart.lapshin.scala.webserver

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http

import scala.concurrent.ExecutionContext

object WebServerApp {

  private implicit val system: ActorSystem[Nothing] =
    ActorSystem(Behaviors.empty, "webserver")

  private implicit val executionContext: ExecutionContext =
    system.executionContext

  def main(args: Array[String]): Unit = {
    val config = AppConfig()

    Http()
      .newServerAt(config.host, config.port)
      .bind(DirInfo(config).routes)
  }
}
