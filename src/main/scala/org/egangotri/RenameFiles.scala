package org.egangotri

import java.io.File

import org.utils.FileUtil

import scala.util.Try

object RenameFiles extends App {

  val replaceableString = "Punjab"
  val replacementString = "Panjab"

  val rootFolder = new java.io.File("E:\\")
  var files: Array[File] = FileUtil.recursiveListFiles(rootFolder).filter(_.getName().endsWith(".pdf"))

  files.foreach { file =>
    if (file.getName.contains(replaceableString)) {
      val newName = file.getName.replace(replaceableString, replacementString)
      println(file.getName + s"-> ${file.getParent}/$newName")
      Try(file.renameTo(new File(file.getParent,newName))).getOrElse(false)
    }
  }
}
