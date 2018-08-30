package org.egangotri

import java.io.FileOutputStream
import java.io.File

import com.itextpdf.text.Document
import com.itextpdf.text.pdf.{PdfReader, PdfWriter}
import com.itextpdf.text.Image
import org.egangotri.ImageItemsToPdfCombiner.getSortedListOfImageItems

/**
  * Created by user on 5/9/2017.
  */
object ImageItemsToPdfCombiner {

/*  val IMAGE_TYPE = ".tif"
  var rootDir = "C:\\hw\\SCL_Hyd"
  var subFolderWithImageItems = "\\OTIFF"
  var pdfSuffix = "_StateCentralLibHyd.pdf" */
  val IMAGE_TYPE = ".JPG"
  var _rootDir = "E:\\EAP-886\\EGangotri\\w"
  var subFolderWithImageItems = "//JPEG"
  var pdfSuffix = ".pdf"
  val fileNamePrefix =  "IMG_"

  def main(args: Array[String]): Unit = {
      processFolder(_rootDir)
  }

  def processFolder(rootDir:String): Unit = {
    val allFolders = new File(rootDir).listFiles.filter(_.isDirectory).toList
    for (folder <- allFolders) {
      var filePath = folder.getAbsolutePath
      val listOfImages = getSortedListOfImageItems(filePath)
      print(listOfImages.mkString(","))
     if (listOfImages.nonEmpty) {
        val newPdfName = s"$filePath$pdfSuffix"
        imageToPDF(newPdfName, filePath)
        val pdfReader = new PdfReader(newPdfName)
        val numberOfPages = pdfReader.getNumberOfPages
        if (numberOfPages != listOfImages.size) {
          println("!!!!Error")
          throw new Exception("Error!!! ")
        }
        else {
          println(s" numberOfPages($numberOfPages == listOfImages.size ${listOfImages.size} ? ${numberOfPages == listOfImages.size}")
        }
      } else
      {
        println(s"$filePath has no ImageItems")
      }
    }
  }
  //http://developers.itextpdf.com/question/how-add-multiple-images-single-pdf




  def imageToPDF(pdfName: String, imgItemLocation: String): Unit = {
    println(s"Procesing ${pdfName}")
    var document: Document = new Document()
    var writer: PdfWriter = null
    try {
      var fos = new FileOutputStream(pdfName)
      writer = PdfWriter.getInstance(document, fos)
      writer.open()
      document.open()

      for (jpg <- getSortedListOfImageItems(imgItemLocation)) {
        val img = Image.getInstance(jpg.getAbsolutePath)
        document.setPageSize(img)
        document.newPage()
        img.setAbsolutePosition(0, 0)
        document.add(img)
      }

    }
    catch {
      case e: Throwable => println(e.printStackTrace())
    }

    finally {
      document.close()
      writer.close()
    }
  }

  def getSortedListOfImageItems(dir: String): List[File] = {
    println(s"getting jpgs from $dir$subFolderWithImageItems")
    var list = List[File]()
    val folderWithImages = new File(dir + subFolderWithImageItems)
    if (folderWithImages.exists && folderWithImages.isDirectory) {
      list = folderWithImages.listFiles.filter(_.getName.toLowerCase.endsWith(IMAGE_TYPE.toLowerCase)).toList.sortWith(sortNumerically)
      println(list.map(_.getName).mkString(","))
    }
    else {
      list = List[File]()
    }
    return list
  }

  //
  def sortNumerically(s1: File, s2: File):Boolean = {
    //print(s"${ s1.getName }  , ${ s2.getName() } ,->> \n")
    replace(s1.getName) < replace(s2.getName)
  }

  //toFloat instead of Int because some values are 170.1 to insert them between 170 and 171
  def replace(fileName:String):Float = {
    fileName.replace(IMAGE_TYPE,"").replaceFirst(fileNamePrefix, "").toFloat
  }

}
