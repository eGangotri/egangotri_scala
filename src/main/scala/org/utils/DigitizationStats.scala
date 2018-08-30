package org.utils

import java.io.File

import org.slf4j.LoggerFactory
import com.itextpdf.text.pdf.PdfReader

import scala.collection.mutable.ListBuffer

/**
  * Created by user on 1/8/2017.
  */
object DigitizationStats {

  val filePath = "C:\\Treasures19"
  val filterText = ".pdf"
  val includeNumPages = true
  val logger = LoggerFactory.getLogger(classOf[AnyRef])

  var numPagesPerFolderList = ListBuffer[Int]()

  def main(args: Array[String]): Unit = {

    def listOfTreausureItems = FileUtil.getListOfFiles(filePath)
    listOfTreausureItems.foreach(x=>logger.info(x.getName))
    logger.info("DigitizationStats")

    var pdfCount:Int = 0
    var totalNumberOfPages:Int = 0
    var totalSize:Double = 0

    for (file <- listOfTreausureItems) {
      var files: Array[File] = FileUtil.recursiveListFiles(file).filter(_.getName().endsWith(".pdf"))

      var fileSizeInMB = files.map(_.length).sum.toDouble
      var numberOfPages = if (includeNumPages) {
        countNumberOfPages(files)
      }
      else {
        0
      }

      pdfCount = pdfCount + files.size
      numPagesPerFolderList += numberOfPages
      totalNumberOfPages = totalNumberOfPages + numberOfPages
      totalSize = totalSize + fileSizeInMB
      System.gc()
      logger.info(s"Number of PDFs in ${file.getName()}: ${ files.size} ${ if (includeNumPages) {s"Number of Pages: $numberOfPages"} } and Total Size in GB: ${toGB(fileSizeInMB)}" )
      logger.info(numPagesPerFolderList.mkString("+"))
    }

    logger.info(s"pdfCount: $pdfCount")
    if (includeNumPages) {logger.info(s"Number of Pages: $totalNumberOfPages")}
    logger.info(s"totalSize in TB: ${toTB(totalSize)}")
    //Last Count of Total Pages: 2918959
  }

  def roundOff(number:Double):Double = {
    BigDecimal(number).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  def toTB(numberInKBs:Double):Double = {
    roundOff(numberInKBs/(1024D*1024D*1024D*1024D))
  }

  def toGB(numberInKBs:Double):Double = {
    roundOff(numberInKBs/(1024D*1024D*1024D))
  }

  def countNumberOfPages(files: Array[File]) = {
    files.map { file =>
      try {
        new PdfReader(file.getAbsolutePath).getNumberOfPages
      }
      catch {

        case e: Throwable => {
          logger.info(s"${file.getAbsolutePath} gave exception while counting pages ${e.getMessage}")
          0
        }
      }
    }.sum
  }
}


object Solution extends App {
  // Approximate the square root of a number.
  //5*5 = 25
  //Sqrt(25) = 5
  //Sqrt(42) = 6.48074069
  //+> Expected Result (with Precision of 0.001 is 6.480 +- 0.001)
  val result = SquareRoot.calculate(42, 0.001)

  println("Square root is : " + result)

}

//Q:/How to find the midle bewteen 250 and 500 ?
//(h+l)/2 =


object SquareRoot {

  // number - number for which the square root should be found
  // eps - precision of the answer i.e = .001
  def calculate(number: Double, eps: Double): Double = {
    val squares = Range(1,(number/2).toInt).map(x=> x*x)
    println(squares.mkString(","))
    val approxCal = squares.filter(num => num < number)
    println(approxCal.mkString(","))
    println(s"The Square root is between ${approxCal.size} - ${approxCal.size + 1}")

    val lowerBound = approxCal.size
    val upperBound = approxCal.size+ 1

    //6.48074069840786

    val squares2 = (lowerBound.toDouble to upperBound.toDouble by 0.1).map(x=> x*x)
    println("squares2: " + squares2.mkString(","))
    Math.sqrt(42)
    val approxCal2 = squares2.filter(num => num <= number)

    println("approxCal2: " + approxCal2.mkString(","))
    val newLowerBound = lowerBound + (approxCal2.size*0.1)
    val newUpperBound = newLowerBound + 0.1

    println(s"The Square root is between $newLowerBound - $newUpperBound")

    return 1

  }


}
