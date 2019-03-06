package com.cads

import com.cads.ConversionFunctions.fromLineToTrip
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Evgeny Borisov
  */
object Start {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Taxi").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rddRawData = sc.textFile("data/taxi_orders.txt")
    val tripRdd = rddRawData.map(fromLineToTrip)
    val bostonTripsRdd = tripRdd.filter(trip => trip.cityName == "boston").persist()
    val numberOfLongTripsToBoston = bostonTripsRdd.filter(_.km > 10).count()
    println(s"number of long trip to boston: $numberOfLongTripsToBoston")
    val totalBostonKm = bostonTripsRdd.map(_.km).sum()
    println(s"total km to boston:$totalBostonKm")


    val id2KmRdd = tripRdd.map(trip => (trip.id, trip.km)).reduceByKey(_ + _)

    val id2NameRdd = sc.textFile("data/drivers.txt").map(_.split(", ")).map(arr => (arr(0), arr(1)))

    val chempions = id2NameRdd.join(id2KmRdd).sortBy(_._2._2,ascending = false).take(3)
    chempions.foreach(println)

  }
}







