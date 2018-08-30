package org.egangotri.eap

import java.io.File
object EAPFileNamingConventionImplementor {

  val SUFFIX = "TIF"
  val SUFFIX_WTH_DOT = s".$SUFFIX"
  val projCode = "EAP886"
  val COUNTER_INITIAL_VALUE:Int = 0
  val rename:Boolean = true

  val mainFolder:String = "G:\\eap_deliverable\\toModify"
  def main(args: Array[String]) :Unit = {
    (new File(mainFolder)).listFiles().foreach(proc)
  }

    def proc(filePath:File):Unit = {
      println(s"proc $filePath")
      var cntr = COUNTER_INITIAL_VALUE
      val fileTitle = filePath.getName
      for (f <- filePath.listFiles().filter(_.getName == SUFFIX).head.listFiles) {
      //for (f <- filePath.listFiles()) {
        if (f.getName.endsWith(SUFFIX_WTH_DOT)) {
          cntr=cntr+1
          var newName = counter(cntr, s"${fileTitle}_")
          var newPath = s"${f.getParent + File.separator + newName}"
          println(s"$f will be renamed to $newPath")
          if(rename) println(f.renameTo(new File(newPath)))
        }
      }
    }

    def counter(cntr:Int , prefix:String, suffix:String = SUFFIX_WTH_DOT):String = {
      return cntr match {
        case x if cntr < 10 => s"${prefix}00$x$suffix"
        case x if cntr < 100 => s"${prefix}0$x$suffix"
        case n => s"${prefix}$n$suffix"
      }
    }
}
