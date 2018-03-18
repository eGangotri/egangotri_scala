package  org.egangotri

import org.utils.FileUtil
import org.egangotri._
import java.io.{BufferedWriter, File, FileWriter}

import scala.collection.mutable.ListBuffer
import scala.io.Source

object CompareLocalFilesWithArchive{
  var fileNameTokenList:ListBuffer[Array[String]] = ListBuffer[Array[String]]()
  val filename = System.getProperty("user.home") + s"${File.separator}eGangotri${File.separator}archiveUserName.txt"

  def main(args: Array[String]): Unit = {
    //invoke JsoupReader to get list of File Names
    JSoupReader.main(args)

    val path:File = new File("C:\\Treasures11\\megha\\pre57")
    val list = FileUtil.recursiveListFiles(path)
    val filesOnlyList:Array[File] = list.filter(_.isFile)
    filesOnlyList.foreach(println)
    for(file <- filesOnlyList) {
      val pattern = "(-|\\(|\\)|_|\\.pdf|,)".r
      val underscoreToSpace = pattern.replaceAllIn(file.getName, " ")
      println(s"${file.getName} -- $underscoreToSpace")
      val tokenizedFileName = underscoreToSpace.split("\\s+")
      fileNameTokenList += tokenizedFileName
    }

    for (e <- fileNameTokenList) {
      println(e.mkString(","))
    }

  }

  def compare() ={

  }

  def readCSV(): Unit = {
    val csv = Source.fromFile(filename).getLines().mkString
    print(csv)
    val fileNamesInArchive = csv.split(",")
    val arr = fileNamesInArchive.map(_.split("//s+"))
  }

}