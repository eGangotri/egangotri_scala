package org.bigdata.skt

import org.apache.spark.{SparkConf, SparkContext}

object PairRDD extends App{
  val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]").set("spark.executor.memory", "1g")
  val sc = new SparkContext(conf)

  val wikis = List(WikiPage(1, "Amazon", "River in SA", "10-10-2001", "A"), WikiPage(1, "Yamuna", "River in India", "10-10-2001", "A"), WikiPage(2,"Ganga", "River in India", "11-10-2001", "B"))
  val wikiRDD = sc.parallelize(wikis)
  val kvRdd = wikiRDD.map(wiki => (wiki.id, wiki))
  val mapRDD = kvRdd.groupByKey()
  mapRDD.collect().foreach(println)
  println(kvRdd.count())
}

case class WikiPage(id:Long, title:String, text:String, createDate:String, author:String)
