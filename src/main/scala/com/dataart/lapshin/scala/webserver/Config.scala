package com.dataart.lapshin.scala.webserver

import scala.io.Source

class Config(val confMap: Map[String, String]) {
  val host: String = confMap("host")
  val port: Int    = confMap("port").toInt
  val dir:  String = confMap("root-dir")
}

object Config {

  def apply(filename: String): Config = {
    val confLines = Source.fromFile(filename).getLines.toList
    val confTuples = confLines.collect(line => line.split("=") match {
      case Array(k, v) => (k, v)
    })
    val confMap = Map() ++ confTuples
    new Config(confMap)
  }
}
