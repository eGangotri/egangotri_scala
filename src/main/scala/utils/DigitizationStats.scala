package org.egangotri.util

import java.io.File

/**
  * Created by user on 1/8/2017.
  */
object DigitizationStats {
  def main(args: Array[String]): Unit = {

    var filePath = "F:\\" //"F:\\Treasures 2"
    var filterText = "Tr"//src"
    def listOfTreausureItems = FileUtil.getListOfFiles(filePath).filter(_.getName().startsWith(filterText))
    //listOfTreausureItems.foreach(println)
    //getSize(listOfTreausureItems)

    var pdfCount:Int = 0
    for (file <- listOfTreausureItems) {
      var x: Array[File] = FileUtil.recursiveListFiles(file).filter(_.getName().endsWith(".pdf"))

      pdfCount = pdfCount + x.size
      println(s"Number of PDFs in ${file.getName()}: ${ x.size}")
    }

    println(s"pdfCount: $pdfCount")
  }




}
