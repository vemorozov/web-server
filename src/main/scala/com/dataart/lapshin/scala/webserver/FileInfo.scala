package com.dataart.lapshin.scala.webserver

import akka.http.scaladsl.model._
import org.apache.commons.io.{FileUtils, FilenameUtils}

import java.io.File

final case class FileInfo(file: File) {

  val name: String =
    file.getName

  private val ext: String =
    FilenameUtils.getExtension(name)

  val contentType: ContentType =
    ext match {
      case "xml"  => ContentTypes.`text/xml(UTF-8)`
      case "html" => ContentTypes.`text/html(UTF-8)`
      case "htm"  => ContentTypes.`text/html(UTF-8)`
      case "png"  => ContentType(MediaTypes.`image/png`)
      case "jpg"  => ContentType(MediaTypes.`image/jpeg`)
      case "jpeg" => ContentType(MediaTypes.`image/jpeg`)
      case "gif"  => ContentType(MediaTypes.`image/gif`)
      case "pdf"  => ContentType(MediaTypes.`application/pdf`)
      case _      => ContentTypes.`text/plain(UTF-8)`
    }

  val httpResponse: HttpResponse = {
    val content = FileUtils.readFileToByteArray(file)
    HttpResponse(entity = HttpEntity(contentType, content))
  }
}
