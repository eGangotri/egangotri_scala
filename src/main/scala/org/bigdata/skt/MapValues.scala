package org.bigdata.skt

import org.apache.spark.{SparkConf, SparkContext}

object MapValues extends App{

  val conf = new SparkConf().setAppName("SparkConf").setMaster("local[2]").set("spark.executor.memory", "1g")
  val sc = new SparkContext(conf)

  val tutions = List(Tution("1st", 200),Tution("1st", 300),Tution("2nd", 400),Tution("3rd", 500),Tution("4th", 600))
  val tutionRdd = sc.parallelize(tutions)
  val pairRdd = tutionRdd.map(tut => (tut.grade, tut.fees))
  val mapValuesRdd = pairRdd.mapValues(_*70)

  val keys = mapValuesRdd.keys

  keys.collect().foreach(println)

  val reducedRdd = mapValuesRdd.reduceByKey(_+_)
  reducedRdd.collect().foreach(println)
  mapValuesRdd.collect().foreach(println)
  println(mapValuesRdd.countByKey())
}

case class Tution(grade:String, fees: Long)
