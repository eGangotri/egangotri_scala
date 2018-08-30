package org.egangotri.eap

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

object EAPFolderNamePageCount {

  val TIF = ".TIF"
  val projCode = "EAP886"

  val mainFolder: String = "E:\\Tiff\\EAP886\\OtherAlms"

  def main(args: Array[String]): Unit = {
    new File(mainFolder).listFiles().foreach(proc)
    new File("E:\\Tiff\\EAP886\\Alm 1 Tiff files").listFiles().foreach(proc)
  }

  def proc(filePath: File): Unit = {
    var fileName = filePath.getName
    var fileTifCount = filePath.list().size
    var time = new SimpleDateFormat("MMM yyyy").format(new Date(filePath.listFiles().head.lastModified()))

    println(s"${fileName} ${fileName}_1_${fileTifCount} $time ${fileTifCount} Tif Files")
  }


}

import java.nio.file.{Files, Paths}
import java.security.MessageDigest

object Generator {
  implicit class Helper(val sc: StringContext) extends AnyVal {
    def md5():    String = generate("MD5", sc.parts(0))
    def sha():    String = generate("SHA", sc.parts(0))
    def sha256(): String = generate("SHA-256", sc.parts(0))
  }
  // t is the type of checksum, i.e. MD5, or SHA-512 or whatever
  // path is the path to the file you want to get the hash of
  def generate(t: String, path: String): String = {
    val arr = Files readAllBytes (Paths get path)
    val checksum = MessageDigest.getInstance(t) digest arr
    checksum.map("%02X" format _).mkString
  }
}