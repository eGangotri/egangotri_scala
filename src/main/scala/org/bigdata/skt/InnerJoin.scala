package org.bigdata.skt

import org.apache.spark.{SparkConf, SparkContext}

object InnerJoin extends App {

  val conf = new SparkConf().setAppName("SparkConf").setMaster("local[2]").set("spark.executor.memory", "1g")
  val sc = new SparkContext(conf)

  val idWithGrades = List((1,50), (2,30), (3,35), (4,43), (6,30))
  val idWithClass = List( (1,2), (2,3), (3,1), (4,3), (5,6))

  val idWithGradesRdd = sc.parallelize(idWithGrades)
  val idWithClassRdd = sc.parallelize(idWithClass)

  idWithGradesRdd.join(idWithClassRdd).sortByKey().collect().foreach(println)
  idWithGradesRdd.leftOuterJoin(idWithClassRdd).sortByKey().collect().foreach(println)
  idWithGradesRdd.rightOuterJoin(idWithClassRdd).sortByKey().collect().foreach(println)



}
