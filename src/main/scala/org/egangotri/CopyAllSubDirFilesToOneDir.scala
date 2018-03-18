package org.egangotri

import java.io.{File}
import org.utils.{FileUtil}
import scala.collection.mutable.ListBuffer
import java.nio.file.{Files, Path, StandardCopyOption}

object CopyAllSubDirFilesToOneDir {

  def main(args: Array[String]): Unit = {
    val path: File = new File("K:\\Bngl\\IndoFrAj\\E Books")
    val dest: File = new File("E:\\bngl2")

    val list3 = FileUtil.recursiveListFiles(path)
    val filesOnlyList = list3.filter(_.isFile) //.filter(_.getName.endsWith(".pdf"))

    println(s"list3: ${list3.size}")
    copyFiles(filesOnlyList, dest)
  }

  def copyFiles(filesOnlyList: Array[File], destFolder: File): Unit = {

    // implicit def toPath (filename: String) = get(filename)
    for (item <- filesOnlyList) {
      val d1 = item.toPath
      val d2 = new File(s"${destFolder}\\${item.getName}").toPath
      println(s"--$d1 \n$d2")
      Files.copy(d1, d2, StandardCopyOption.REPLACE_EXISTING)
    }
  }


}