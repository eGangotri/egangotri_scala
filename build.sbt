
name := "egangotri_in_Scala"

version := "1.0"

scalaVersion := "2.11.8"

//To run: sbt "run MoonGazer"
//If using arguments Quotes must!
//mainClass in Compile := Some("org.egangotri.JSoupReader")
//mainClass in Compile := Some("org.egangotri.CopyAllSubDirFilesToOneDir")
//mainClass in Compile := Some("org.egangotri.CompareFolders")
//mainClass in Compile := Some("org.egangotri.FileListWithFileInitialText")
//mainClass in Compile := Some("txn.ReadFile")
mainClass in Compile := Some("org.egangotri.EAPFileNamingConventionImplementor")

//mainClass in Compile := Some("org.bigdata.skt.WordCount")

unmanagedBase <<= baseDirectory { base => base / "lib" }

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.1.0"  % "provided",
  "org.scala-lang" % "scala-library" % "2.11.7",
  "org.scala-lang" % "scala-compiler" % "2.11.7",
  "org.scala-lang" % "scala-reflect" % "2.11.7",
  "jline"  % "jline" % "2.12.1",
  "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.4",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4",
  "org.jsoup" % "jsoup" % "1.10.2",
  "com.itextpdf" % "itextpdf" % "5.5.11",
  "org.scalaj" % "scalaj-http_2.10" % "2.3.0"
  //,"org.slf4j" % "log4j-over-slf4j" % "1.7.13"
)

assemblyMergeStrategy in assembly := {
  case PathList("bigDataForSkt") => MergeStrategy.discard
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}

assemblyOutputPath in assembly := file("bigDataForSkt")
