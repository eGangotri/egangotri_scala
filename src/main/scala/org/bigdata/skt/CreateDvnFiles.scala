package org.bigdata.skt

import com.egangotri.util.EncodingUtil
import com.egangotri.constants._
import org.jsoup.Jsoup
import java.io._
import java.nio.charset.StandardCharsets
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.Writer
import org.jsoup.select.Elements

import scalaj.http.{Http, HttpOptions, HttpRequest}

object CreateDvnFiles {
  val inputEncoding = Constants.IAST
  val outputEncoding = Constants.UNICODE_DVN
  val capitalizeIAST = false
  var rootDir = "./bigDataForSkt/"
  var outputDir = s"$rootDir/cnv/"
  var abhinavanWorksList =

    List("http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FIPV_HK.txt&miri_catalog_number=M00019",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FIPVV_1_hk.txt&miri_catalog_number=M00020",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FIPVV_2_hk.txt&miri_catalog_number=M00021",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FIPVV_3_hk.txt&miri_catalog_number=M00022",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FTantraloka-1-14-HK.txt&miri_catalog_number=M00092",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FTantraloka-15-37-HK.txt&miri_catalog_number=M00093",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2Fnatyrasatrav2HK.txt&miri_catalog_number=M00361",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2Fnatyasatrav1HK.txt&miri_catalog_number=M00359",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2Fnatyasatrav3HK.txt&miri_catalog_number=M00362",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FparamarthasaraHK.txt&miri_catalog_number=M00041",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FParaatrimsikavivaranaHK.txt&miri_catalog_number=M0409",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FparatrimsikavivaranaHK.txt&miri_catalog_number=M00154",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FparamarthasaraHK.txt&miri_catalog_number=M00041",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FParaatrimsikavivaranaHK.txt&miri_catalog_number=M0409",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FparatrimsikavivaranaHK.txt&miri_catalog_number=M00154",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FparaatriisikaalaghuvrttiHK.txt&miri_catalog_number=M00042",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FbodhapancadasikaaHK.txt&miri_catalog_number=M00173",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FmaliniijayavartikaHK.txt&miri_catalog_number=M00158",
      "http://muktalib5.org/DL_CATALOG/DL_CATALOG_USER_INTERFACE/dl_user_interface_create_utf8_text.php?hk_file_url=..%2FTEXTS%2FETEXTS%2FIPV_HK.txt&miri_catalog_number=M00019"
    )
  //var urlList = List("http://muktalib5.org")

  import scalaj.http.{Http, HttpOptions}

  def main(args: Array[String]): Unit = {
    createDevanagariFiles()
  }

  def createDevanagariFiles() {
    val outputDirFile = new File(outputDir)
    if (checkNonEmptyRootAndOutputDirectoryExistAndIfNotThenCreate()){
      abhinavanWorksList.foreach { _url =>
        //http://qiita.com/bmj0114/items/d4111b1a1b8bd77199c3
        val http_response = Http(_url).auth("muktabodha", "indology")
          .asString
        val doc = Jsoup.parse(http_response.body)
        val contents = doc.select("pre").first.text
        val masthead: Elements = doc.select("span.listItem")
        val iterator = masthead.iterator
        val fileName = scala.collection.mutable.ArrayBuffer.empty[String]
        while (iterator.hasNext) {
          val txt = iterator.next().text
          fileName += txt.split(" ")(0)
        }
        println(fileName.mkString("_"))
        createFile(s"$outputDir${fileName.mkString("_")}.txt", convertFromIASTToUnicodeDVN(contents))
      }
    }
  }

  def convertFromIASTToUnicodeDVN(contents: String): String = {
    EncodingUtil.convert(contents, inputEncoding, outputEncoding, capitalizeIAST)
  }

  def createFile(fileName: String, content: String): Unit = {
    println("createFile $fileName")
    val _out: Writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(s"$fileName"), StandardCharsets.UTF_8))
    try {
      _out.write(content)
    }
    finally _out.close()
  }

  def checkNonEmptyRootAndOutputDirectoryExistAndIfNotThenCreate(): Boolean = {
    new File(rootDir).mkdirs()
    new File(outputDir).mkdirs()
    new File(outputDir).listFiles.isEmpty
  }
}
