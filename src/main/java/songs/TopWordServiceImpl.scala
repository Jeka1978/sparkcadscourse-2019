package songs

import org.apache.spark.SparkContext

/**
  * @author Evgeny Borisov
  */
class TopWordServiceImpl(sc:SparkContext) extends TopWordsService {
  override def topX(fileName: String, x: Int): List[String] = {
    sc.textFile(fileName).map(_.toLowerCase)
      .flatMap(_.split("\\W+"))
      .map(word=>(word,1))
      .reduceByKey(_+_)
      .map(_.swap)
      .sortByKey(ascending = false)
      .top(x).toList.map(_._2)
  }
}
