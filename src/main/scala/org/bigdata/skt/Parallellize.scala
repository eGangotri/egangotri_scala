package org.bigdata.skt

import org.apache.spark.{SparkConf, SparkContext}

object Parallellize extends App{

  val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]").set("spark.executor.memory", "1g")
  val sc = new SparkContext(conf)
  val persons = List(Person("CP", "Tech Lead"), Person("RP", "Doctor"))
  val personRDD = sc.parallelize(persons)
  val filters = personRDD.filter(_.name == "CP")
  filters.foreach(println)
}

case class Person(name:String, designation:String)
