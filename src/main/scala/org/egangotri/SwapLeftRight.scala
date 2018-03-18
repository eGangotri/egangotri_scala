package org.egangotri
import org.utils.{FileUtil}
import java.io.File
/**
  * Created by user on 12/1/2016.
  */
object SwapLeftRight {
    def main(args: Array[String]): Unit = {
      def folderName:String = "C:\\hw\\Guru Grantha Pradeep"

      var files:List[File] = FileUtil.getListOfFiles(folderName)
      //files.foreach(i => print(s"$i,"))

      for(file:File <- files){
        print (s" before: ${file.getName}")
        def fileDigits:Int = file.getName.substring(0,3).toInt

        if(file.getName.contains("_L")){
          print (s" contains _L ") ///${file.getAbsolutePath} - ${new File(file.getAbsolutePath.replace("_L","_xr")).exists()}")
          println (s" after: ${file.getAbsolutePath.replace(s"${fileDigits}_L",s"${fileDigits+1}")}")

          file.renameTo(new File(file.getAbsolutePath.replace(s"${fileDigits}_L",s"${fileDigits+1}")))
        }

        else  if(file.getName.contains("_R")){
          print (s" contains _R")
          println (s" after: ${file.getAbsolutePath.replace(s"${fileDigits}_R",s"${fileDigits-1}")}")

          file.renameTo(new File(file.getAbsolutePath.replace(s"${fileDigits}_R",s"${fileDigits-1}")))
        }

      }
    }
}
