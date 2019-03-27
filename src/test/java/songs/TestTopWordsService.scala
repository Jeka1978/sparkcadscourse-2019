package songs

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.{Assert, Test}

/**
  * @author Evgeny Borisov
  */
class TestTopWordsService {

  @Test
  def testTopX():Unit={
    val conf = new SparkConf().setAppName("songs").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val wordService = new TopWordServiceImpl(sc)
    val topWords = wordService.topX("data/songs/beatles/*",3)
    println(topWords)
    Assert.assertEquals("yesterday",topWords.head)

  }

}














