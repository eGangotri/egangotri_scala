package org.egangotri
import java.io.File

import org.utils.{FileUtil}

import scala.collection.mutable.ListBuffer
/**
  * Created by user on 11/23/2016.
  */
object CheckMissingPics {
  def main(args: Array[String]) {

    var fileNames:List[String] = FileUtil.getListOfFilesAsListOfStrings("C:\\hw\\avn\\AvnManuscripts\\ShriVidya Nity Puja Paddhti_Alm_25_Shlf_3_Gha_Devanagari - Tantra")
    fileNames.foreach(i => print(s"$i,"))

    var fileNums:ListBuffer[Int] = (6900 to 7223).to[ListBuffer]
    println(s"\nnew init of fileNums: ${fileNums.size} ---")
    fileNums.foreach(i => print(s"$i,"))

    println("\nfileNames int extractor")
    for(fileName:String <- fileNames){
      var num:Int = fileName.substring(4, 8).toInt
      print (s",$num")
      fileNums -= num
      print(s"(${fileNums.size})")

    }

    println(s"\nremaining ${fileNums.size} ---")
    fileNums.foreach(i => println(s"IMG_$i.JPG,"))

  }


}
