package org.egangotri

import java.io.FileOutputStream
import java.io.File

import com.itextpdf.text.Document
import com.itextpdf.text.pdf.{PdfReader, PdfWriter}
import com.itextpdf.text.Image

/**
  * Created by user on 5/9/2017.
  */
object ImageItemsToPdfCombiner {

/*  val IMAGE_TYPE = ".tif"
  var rootDir = "C:\\hw\\SCL_Hyd"
  var subFolderWithImageItems = "\\OTIFF"
  var pdfSuffix = "_StateCentralLibHyd.pdf" */
  val IMAGE_TYPE = ".jpg"
  var _rootDir = "F:\\Kurukshetra University\\Manuscripts\\Gurumukhi"
  var subFolderWithImageItems = ""
  var pdfSuffix = " - Gurumukhi Manuscripts at Kurukshetra University.pdf"

  def main(args: Array[String]): Unit = {
      processFolder(_rootDir)
  }

  def processFolder(rootDir:String): Unit ={
    val allFolders = new File(rootDir).listFiles.filter(_.isDirectory).toList
    for (folder <- allFolders) {
      var filePath = folder.getAbsolutePath
      val listOfImages = getListOfImageItems(filePath)
      listOfImages.foreach(println)
      if (listOfImages.nonEmpty) {
        val newPdfName = s"$filePath$pdfSuffix"
        imageToPDF(newPdfName, filePath)
        val pdfReader = new PdfReader(newPdfName)
        val numberOfPages = pdfReader.getNumberOfPages()
        if (numberOfPages != listOfImages.size) {
          println("!!!!Error")
        }
        else {
          println(s"${numberOfPages == listOfImages.size}")
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

      for (jpg <- getListOfImageItems(imgItemLocation)) {
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

  def getListOfImageItems(dir: String): List[File] = {
    println(s"getting jpgs from $dir$subFolderWithImageItems")
    var list = List[File]()
    val d = new File(dir + subFolderWithImageItems)
    if (d.exists && d.isDirectory) {
      list = d.listFiles.filter(_.getName.toLowerCase.endsWith(IMAGE_TYPE)).toList.sortWith(sortNumerically) //_.lastModified() > _.lastModified())
      list.foreach(println)
    }
    else {
      list = List[File]()
    }
    return list
  }

  def sortNumerically(s1: File, s2: File):Boolean = {
    //print(s"${ s1.getName }  , ${ s2.getName() } ,->> \n")
    s1.getName.replaceFirst(IMAGE_TYPE,"").toInt < s2.getName.replaceFirst(IMAGE_TYPE,"").toInt
  }

}
