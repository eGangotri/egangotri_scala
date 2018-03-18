package org.bigdata.skt

import java.io.File

import org.apache.commons.io.FileUtils
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd.{MapPartitionsRDD, RDD}

import scala.reflect.ClassTag

object WordCount {
  def main(args: Array[String]): Unit = {
    // Create a Scala Spark Context.
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]").set("spark.executor.memory", "1g")
    val sc = new SparkContext(conf)
    System.setProperty("hadoop.home.dir", "C:/Spark")

    println(s"args: ${args.mkString(",")}")

    if (args.length > 0 && args(0).length > 0) {
      System.setProperty("hadoop.home.dir", args(0))
    }

    CreateDvnFiles.createDevanagariFiles()
    println("hadoop.home.dir:hadoop.home.dir: " + System.getProperty("hadoop.home.dir"))


    try {
      val allFilesRDD = sc.textFile(CreateDvnFiles.outputDir).repartition(1)
      // Split it up into words.
      val allWordsRDD = allFilesRDD.flatMap(line => line.split("\\s+"))

      val allWordsListDumpLoc = CreateDvnFiles.rootDir + "sprk-all-word-list"
      rmExistingDirAndSaveAsTextFilemap(allWordsListDumpLoc, allWordsRDD)

      val allWordsFilteredRDD = allWordsRDD.map(word =>
        word.replaceAll("[\\<\\(\\[\\{\\\\\\^\\-\\=\\$\\!\\|\\]\\}\\)??\\?\\*\\+\\.\\>\"\'&,@_;:]", "")
      )

      //CreateDvnFiles.createFile(CreateDvnFiles.rootDir + "a.txt", allWordsRDD.filter(_.contains("\"")).take(50).mkString("\n"))
      //CreateDvnFiles.createFile(CreateDvnFiles.rootDir + "b.txt", allWordsFilteredRDD.take(500).mkString("\n"))

      val allWordsListFilteredDumpLoc = CreateDvnFiles.rootDir + "sprk-all-filtered-word-list"
      rmExistingDirAndSaveAsTextFilemap(allWordsListFilteredDumpLoc, allWordsRDD)

      var wordCountRDD = allWordsFilteredRDD.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
      wordCountRDD = wordCountRDD.sortByKey()

      val allWordCountListDumpLoc = CreateDvnFiles.rootDir + "sprk-all-word-count-list"
      rmExistingDirAndSaveAsTextFilemap(allWordCountListDumpLoc, wordCountRDD)
    }
    catch {
      case e: Throwable => println("Got some other kind of exception" + e.printStackTrace())
    }
    finally {
      println("**finally***")
      sc.stop
    }
  }

  def rmExistingDirAndSaveAsTextFilemap[U: ClassTag](fileDumpLoc: String, _rdd: RDD[U]): Unit = {
    println(s"fileDumpLoc: ${fileDumpLoc}")
    val file: File = new File(fileDumpLoc)
    FileUtils.deleteDirectory(file)
    _rdd.saveAsTextFile(fileDumpLoc)
  }
}
