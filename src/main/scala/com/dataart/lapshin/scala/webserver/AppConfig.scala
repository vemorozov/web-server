package com.dataart.lapshin.scala.webserver

import com.typesafe.config.ConfigFactory

class AppConfig() {

  private val config = ConfigFactory.load()

  val host: String = config.getString("akka.webserver.host")
  val port: Int    = config.getInt("akka.webserver.port")
  val dir:  String = config.getString("akka.webserver.root-dir")
}
