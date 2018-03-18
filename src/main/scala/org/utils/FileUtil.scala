package org.utils

import java.io.File

/**
  * Created by user on 12/1/2016.
  */
object FileUtil {
  def getListOfFilesAsListOfStrings(dir: String):List[String] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.list.toList
    } else {
      List[String]()
    }
  }

  def getListOfFiles(dir: String):List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles().toList
    } else {
      List[File]()
    }
  }

  def recursiveListFiles(f: File): Array[File] = {
    val these = f.listFiles
    these ++ these.filter(_.isDirectory).flatMap(recursiveListFiles)
  }

}
