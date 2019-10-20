package org.bigdata.skt

import org.apache.spark.{SparkConf, SparkContext}

object LogCount {

  var rootDir = "./bigDataForSkt/"
  var outputFile = s"$rootDir/catalina.2016-04-16.log"

  def main(args: Array[String]): Unit = {
    // Create a Scala Spark Context.
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]").set("spark.executor.memory", "1g")
    val sc = new SparkContext(conf)
    System.setProperty("hadoop.home.dir", "C:/Spark")

    val outputRdd = sc.textFile(outputFile)
    val lines = outputRdd.flatMap(_.split("\n"))
    lines.take(10).foreach(println)
    val filtered1 = lines.filter(line => line.contains("14-May-2016") && line.contains("Error"))
    println(s"count: ${filtered1.count}")
    val filtered2 = lines.filter(_.contains("16-Apr-2016"))
    println(s"count2: ${filtered2.count}")
    filtered1.saveAsTextFile(rootDir + "error")

  }

}
