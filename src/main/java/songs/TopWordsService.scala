package songs

/**
  * @author Evgeny Borisov
  */
trait TopWordsService {
  def topX(fileName:String,x:Int):List[String]
}
