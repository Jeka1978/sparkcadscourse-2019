import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author Evgeny Borisov
  */
object Start {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Taxi").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rddRawData = sc.textFile("data/taxi_orders.txt")


  }
}
