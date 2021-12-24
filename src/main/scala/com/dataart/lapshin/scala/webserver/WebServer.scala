package com.dataart.lapshin.scala.webserver

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object WebServer {

  implicit val system: ActorSystem[Nothing] =
    ActorSystem(Behaviors.empty, "webserver")

  implicit val executionContext: ExecutionContext =
    system.executionContext

  def main(args: Array[String]): Unit = {

    val conf = Config(args(0))

    val bindingFuture = Http()
      .newServerAt(conf.host, conf.port)
      .bind(DirInfo(conf).routes)

    println(s"Server online at http://${conf.host}:${conf.port}/\n" +
             "Press RETURN to stop...")
    StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate)
  }
}
