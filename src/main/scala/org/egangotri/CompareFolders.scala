package org.egangotri

import java.io.File

/**
  * Created by user on 6/24/2017.
  */
object CompareFolders {
  def main(args: Array[String]): Unit = {

    val folder1 = "e:\\pellegrini\\Indo"
    val folder2 = "G:\\pellegrini\\Indo"

    procFolders(folder1, folder2)

    getAllDirectories(folder1)
  }

  def procFolders(folder1: String, folder2: String): Unit = {
    if(folder1 == folder2){
      println ("Folder Names provided are same")
      return
    }
    val folder1Contents = getAllDirectories(folder1)
    val folder2Contents = getAllDirectories(folder2)

    var fileStats = scala.collection.mutable.ListBuffer[scala.collection.mutable.ListBuffer[String]]()

    for (cntnt <- folder1Contents) {
      val size = countOfFiles(cntnt)
      val fileStat: scala.collection.mutable.ListBuffer[String] = scala.collection.mutable.ListBuffer[String]()
      fileStat += cntnt.getName
      fileStat += cntnt.length.toString
      fileStat += size.toString
      fileStats += fileStat
      //println (s"$cntnt $size")
    }

    var counter = 0
    for (cntnt <- folder2Contents) {
      val size = countOfFiles(cntnt)
      val fileStat: scala.collection.mutable.ListBuffer[String] = scala.collection.mutable.ListBuffer[String]()
      fileStats(counter) += cntnt.getName
      fileStats(counter) += cntnt.length.toString
      fileStats(counter) += size.toString
      counter += 1
      //println (s"$cntnt $size")
    }


    for (fileStat <- fileStats) {
      //fileStat.foreach(println(_))
      println(s"${fileStat(0)} ${fileStat(1)} ${fileStat(2)} ${fileStat(3)} ${fileStat(4)} ${fileStat(5)}")
      if (fileStat(2) != fileStat(5)) {
        println(s"\t*****size discrepancy ${fileStat(1)} != ${fileStat(4)}")
      }
    }
  }

  def getAllDirectories(folder: String): List[File] = {
    return new File(folder).listFiles.filter(_.isDirectory).toList
  }

  def countOfFiles(folder: File): Int = {
    //return folder.listFiles.filter(!_.isDirectory).toList.length
    return folder.listFiles.toList.length
  }

}
