package org.egangotri

import org.utils.FileUtil

import scala.collection.mutable.ArrayBuffer

/**
  * Created by user on 7/17/2017.
  */
object FileListWithFileInitialText {
  def main(args: Array[String]): Unit = {
    var fileNames:List[String] = FileUtil.getListOfFilesAsListOfStrings("C:\\ORL manuscripts (complete)\\pdf\\u2")
    var fileInits:scala.collection.mutable.ArrayBuffer[String] = ArrayBuffer()

    for(name <- fileNames){
      fileInits += name.split(" ")(0)
    }

    //
    //
    // fileInits.foreach(println)
    for(name <- fileInits){
      print(s"'$name', ")
    }
  }

}
