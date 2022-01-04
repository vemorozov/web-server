package com.dataart.lapshin.scala.webserver

import com.typesafe.config.{Config, ConfigFactory}

class AppConfig(val config: Config) {

  val host: String = config.getString("akka.webserver.host")
  val port: Int    = config.getInt("akka.webserver.port")
  val dir:  String = config.getString("akka.webserver.root-dir")
}

object AppConfig {

  def apply(filename: String = "application.conf"): AppConfig = {
    new AppConfig(ConfigFactory.load(filename))
  }
}
