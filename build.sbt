
name := "eGangotri_Scala"

version := "1.0"

scalaVersion := "2.11.8"

//To run: sbt "run MoonGazer"
//If using arguments Quotes must!
//mainClass in Compile := Some("org.egangotri.JSoupReader")
//mainClass in Compile := Some("org.egangotri.CopyAllSubDirFilesToOneDir")
//mainClass in Compile := Some("org.egangotri.CompareFolders")
//mainClass in Compile := Some("org.egangotri.FileListWithFileInitialText")
//mainClass in Compile := Some("txn.ReadFile")
mainClass in Compile := Some("org.egangotri.ImageItemsToPdfCombiner")
//mainClass in Compile := Some("org.bigdata.skt.WordCount")

//mainClass in Compile := Some("org.bigdata.skt.WordCount")

unmanagedBase <<= baseDirectory { base => base / "lib" }

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.1.0"  /*% "provided"*/,
  "org.scala-lang" % "scala-library" % "2.11.7",
  "org.scala-lang" % "scala-compiler" % "2.11.7",
  "org.scala-lang" % "scala-reflect" % "2.11.7",
  "jline"  % "jline" % "2.12.1",
  "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.4",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4",
  "org.jsoup" % "jsoup" % "1.10.2",
  "com.itextpdf" % "itextpdf" % "5.5.11",
  "org.scalaj" % "scalaj-http_2.10" % "2.3.0",
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "org.slf4j" % "slf4j-simple" % "1.7.25"
)

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-actor
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.16"

assemblyMergeStrategy in assembly := {
  case PathList("bigDataForSkt") => MergeStrategy.discard
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}

assemblyOutputPath in assembly := file("bigDataForSkt")
