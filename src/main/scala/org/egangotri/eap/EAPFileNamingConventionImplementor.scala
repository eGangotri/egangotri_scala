package org.egangotri.eap

import java.io.File
object EAPFileNamingConventionImplementor {

  val SUFFIX = "TIF"
  val SUFFIX_WTH_DOT = s".$SUFFIX"
  val projCode = "EAP886"
  val COUNTER_INITIAL_VALUE:Int = 0
  val rename:Boolean = true

  val mainFolder:String = "F:\\Aditya 2nd\\EAP886_Shabda Kalpa Drum_Kand_Supplement_No_Id_Assigned"
  def main(args: Array[String]) :Unit = {


    val fileTitle = new File(mainFolder).getName
    println(s"fileTitle:$fileTitle")
    new File(mainFolder).listFiles().filter(_.getName == "TIFF").foreach(x=> proc(x,fileTitle) )
  }

    def proc(filePath:File, fileTitle:String):Unit = {
      println(s"proc $filePath")
      var cntr = COUNTER_INITIAL_VALUE
      println(s"fileTitle:$fileTitle")
      println(s"filePath:$filePath")

      for (f <- filePath.listFiles()) {
        if (f.getName.endsWith(SUFFIX_WTH_DOT)) {
          cntr=cntr+1
          var newName = counter(cntr, s"${fileTitle}_")
          var newPath = s"${f.getParent + File.separator + newName}"
          println(s"$f will be renamed to $newPath")
          if(rename) println(f.renameTo(new File(newPath)))
        }
      }
      println("dont forget checksum with FASTSUM. you can use File-> Calc Wizard for larger folders")
    }

    def counter(cntr:Int , prefix:String, suffix:String = SUFFIX_WTH_DOT):String = {
      return cntr match {
        case x if cntr < 10 => s"${prefix}00$x$suffix"
        case x if cntr < 100 => s"${prefix}0$x$suffix"
        case n => s"${prefix}$n$suffix"
      }
    }
}
