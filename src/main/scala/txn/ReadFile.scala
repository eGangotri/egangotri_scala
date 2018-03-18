package txn

import scala.io.Source

object ReadFile {
  def main(args: Array[String]): Unit = {
    val filePath = "C:\\Users\\user\\eGangotri\\txn.txt"
    var lines:List[String] = readFile(filePath)

    for(line<-lines){
      println(line)
     // val x = line.split(",")
    }
  }

  def readFile(filePath:String): List[String] = {
    Source.fromFile(filePath).getLines().toList
  }
}
