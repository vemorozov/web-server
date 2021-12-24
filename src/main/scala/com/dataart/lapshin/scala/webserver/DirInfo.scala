package com.dataart.lapshin.scala.webserver

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import org.apache.commons.io.FileUtils

import java.io.File
import scala.annotation.tailrec

class DirInfo(val conf: Config) {

  type FileRoute = Map[String, HttpResponse]
  type Routes = Map[String, FileRoute]

  def routes: Route = ctx => {
    val routes = getRoutes.map {
      case (segment, responses) =>
        get {
          path(segment) {
            entity(as[HttpRequest]) { request =>
              complete(responses.get(request.getUri.path))
            }
          }
        }
    }
    concat(routes.toList: _*)(ctx)
  }

  private def getRoutes: Routes = {

    def toFileRoute(file: File): (String, FileRoute) = {
      val fileInfo = FileInfo(file)
      (fileInfo.name, Map(s"/${fileInfo.name}" -> fileInfo.httpResponse))
    }

    @tailrec
    def loop(files: List[File], routes: Routes): Routes =
      files match {
        case List() => routes
        case file :: rest => loop(rest, routes + toFileRoute(file))
      }

    val files = FileUtils.getFile(conf.dir).listFiles.toList
    addRootResponse( loop(files, Map()) )
  }

  private def addRootResponse(routes: Routes): Routes = {

    val links = routes.keySet.map(name =>
      s"<a href=\"http://${conf.host}:${conf.port}/$name\">$name</a><br />"
    ).foldLeft("")(_ + _)

    val rootResponse = HttpResponse(entity = HttpEntity(
      ContentTypes.`text/html(UTF-8)`,
      s"<html><body>$links</body></html>"))

    routes + ("" -> Map("/" -> rootResponse))
  }
}

object DirInfo {
  def apply(conf: Config): DirInfo = new DirInfo(conf)
}
