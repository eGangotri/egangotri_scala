package org.bigdata.skt

import org.apache.spark.{SparkConf, SparkContext}

object ReduceByKey extends App{

  val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]").set("spark.executor.memory", "1g")
  val sc = new SparkContext(conf)
  val events = List( Event("Talk", 100), Event("Talk", 500),Event("Dance", 200),Event("Music", 300),Event("Dance", 600))
  val eventsRdd = sc.parallelize(events)
  val pairRdd = eventsRdd.map(event => (event.eventName, event.budget))
  pairRdd.collect().foreach(println)
  pairRdd.reduceByKey( _+_).collect.foreach(println)

}

case class Event(eventName:String, budget:Long)