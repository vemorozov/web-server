package com.dataart.lapshin.scala.webserver

import akka.http.scaladsl.model.{ContentType, ContentTypes, HttpEntity, HttpResponse, MediaTypes}
import org.apache.commons.io.{FileUtils, FilenameUtils}

import java.io.File

class FileInfo(val file: File) {

  val name: String =
    file.getName

  private val ext: String =
    FilenameUtils.getExtension(name)

  val contentType: ContentType =
    ext match {
      case "txt"  => ContentTypes.`text/plain(UTF-8)`
      case "xml"  => ContentTypes.`text/xml(UTF-8)`
      case "html" => ContentTypes.`text/html(UTF-8)`
      case "htm"  => ContentTypes.`text/html(UTF-8)`
      case "png"  => ContentType(MediaTypes.`image/png`)
      case "jpg"  => ContentType(MediaTypes.`image/jpeg`)
      case "jpeg" => ContentType(MediaTypes.`image/jpeg`)
      case "gif"  => ContentType(MediaTypes.`image/gif`)
      case "pdf"  => ContentType(MediaTypes.`application/pdf`)
    }

  val httpResponse: HttpResponse = {
    val content = FileUtils.readFileToByteArray(file)
    HttpResponse(entity = HttpEntity(contentType, content))
  }
}

object FileInfo {
  def apply(file: File): FileInfo = new FileInfo(file)
}
