package com.cads

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Evgeny Borisov
  */
object Start {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Taxi").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rddRawData = sc.textFile("data/taxi_orders.txt")
    val tripRdd = rddRawData.map(_.split(" ")).map(arr => Trip(arr(0), arr(1).toLowerCase, arr(2).toInt))
    val bostonTripsRdd = tripRdd.filter(trip => trip.cityName == "boston").persist()
    val numberOfLongTripsToBoston = bostonTripsRdd.filter(_.km > 10).count()
    println(s"number of long trip to boston: $numberOfLongTripsToBoston")
    val totalBostonKm = bostonTripsRdd.map(_.km).sum()
    println(s"total km to boston:$totalBostonKm")
  }
}







