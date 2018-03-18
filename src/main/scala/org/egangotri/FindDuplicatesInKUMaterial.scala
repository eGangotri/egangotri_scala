package org.egangotri

import java.io.File

/**
  * Created by user on 6/23/2017.
  */
object FindDuplicatesInKUMaterial {
    var rootDir = "F:\\Otro\\src_ku_pdfs"
    var names = scala.collection.mutable.ListBuffer[String]()
    var duplicates = scala.collection.mutable.ListBuffer[String]()
    val pdfs = new File(rootDir).listFiles.filter(_.getName.endsWith(".pdf")).toList
    for (pdf <- pdfs) {
      val firstPortion = pdf.getName.split("SP_")
      println(s"$firstPortion")
      if (firstPortion != null && (firstPortion.size > 1)) {
        if (names.contains(firstPortion(2))) {
          duplicates += firstPortion(2)
        }
        names += firstPortion(2)
      }
    }
    duplicates.foreach(println)
}
