package org.egangotri

import java.io.File
object EAPFileNamingConventionImplementor {

  val TIF = ".TIF"
  val projCode = "EAP886"

  val mainFolder:String = "E:\\Tiff\\EAP886"
  def main(args: Array[String]) :Unit = {
    (new File(mainFolder)).listFiles().foreach(proc)
  }

    def proc(filePath:File):Unit = {
      var cntr = 0
      for (f <- filePath.listFiles()) {
        if (f.getName.endsWith(TIF)) {
          cntr=cntr+1
          var newName = counter(cntr, s"${filePath.getName}_")
          var newPath = s"${f.getParent + File.separator + newName}"
          println(s"$f will be renamed to $newPath")
          //println(f.renameTo(new File(newPath)))
        }
      }
    }

    def counter(cntr:Int , prefix:String, suffix:String = s"$TIF"):String = {
      return cntr match {
        case x if cntr < 10 => s"${prefix}00$x$suffix"
        case x if cntr < 100 => s"${prefix}0$x$suffix"
        case n => s"${prefix}$n$suffix"
      }
    }
}
