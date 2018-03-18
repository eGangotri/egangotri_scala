package org.egangotri

import java.io.{BufferedWriter, File}

import org.jsoup.nodes.{Document, Element}
import org.jsoup.Jsoup
import org.jsoup.select.Elements

import scala.collection.mutable.ListBuffer
import scala.io.Source

import java.io.BufferedWriter
import java.io.FileWriter

object JSoupReader {

  val BASE_URL = "https://archive.org/details/"
  val URL_QUERY = "?tab=uploads&sort=-publicdate&page="
  var archiveUserName = ""
  var fileNames = new ListBuffer[Array[String]]()
  val filename = System.getProperty("user.home") + s"${File.separator}eGangotri${File.separator}archiveUserName.txt"

  def main(args: Array[String]): Unit = {

    archiveUserName = Source.fromFile(filename).getLines().mkString
    if (!archiveUserName.startsWith("@")) archiveUserName = s"@$archiveUserName"
    println(s"archiveUserName:$archiveUserName")
    var totalPages: Int = getTotalNumOfUploads()
    println(s"totalPages:$totalPages")
    readDetailsPage(totalPages)

    var counter: Int = 0
    for (fileName <- fileNames) {
      counter += 1
        println(s"$counter " + fileName(0))
    }
    writeToFileAsCSV()
  }

  def writeToFileAsCSV(): Unit = {
    val file = new File(System.getProperty("user.home") + s"${File.separator}eGangotri${File.separator}archiveFileName.txt")
    val csv = fileNames.map(_.mkString("-")) mkString ","
    print (csv)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(csv)
    bw.close()
  }

  def getTotalNumOfUploads(): Int = {
    var path = "#tabby-uploads > div > div.columns-facets > h3"
    val url = s"$BASE_URL$archiveUserName"
    val doc: Document = Jsoup.connect(url).get()
    var titles: Elements = doc.select(path);

    var totalPageNum = titles.text().split(" ").head.replaceAll(",","").toInt
    totalPageNum
  }

  def readDetailsPage(totalPages: Int): Unit = {

    var maxPaginationPerCentum = totalPages / 100
    for (pageNum <- 0 to maxPaginationPerCentum) {
      val url = s"$BASE_URL$archiveUserName$URL_QUERY" + (pageNum + 1)
      val doc: Document = Jsoup.connect(url).get()

      var path = "div.item-ttl.C.C2 > a"
      var titles: Elements = doc.select(path)
      val it = titles.iterator

      while (it.hasNext){
        var title = it.next().attributes.get("title")
        val titleArr: Array[String] = title.split("//s+")
        fileNames += titleArr
      }

    }

  }

}
