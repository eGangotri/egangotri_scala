# eGangotriInScala
Scala Tools for eGangotri

##Prerequisites
Download Java 8 and Scala 2.11. 
Download egangotri_in_scala Code.

## To Use Archive Scrapper
In Home Directory of your Operating System, create the following file:
~/eGangotri/archiveUserName.txt
where ~ is your HOME Directory.

In the File enter the archive username( with or without the '@' Sign).
 
Specify:
mainClass in Compile := Some("org.egangotri.JSoupReader")
in build.sbt

The Corresponding Archive Account will be scrapped for File Names.

run with sbt run

## To use the BigData For Sanskrit Project

####About
This Project will take as input, multiple .txt Files with Unicode encodings placed in 
./bigDataForSkt/cnv Folder.
 
These will be processed and churn out a Word Frequency Set of all Used Words.

These generated Stats can be seen in ./bigDataForSkt/sprk-all-word-count-list/part-00000 

As the Project evolves we will add multiple Big Data Capabilities.

#### Sample Data
This Project takes a Sample Data of Abhinavan Writings using the IAST Texts available at Muktabodha.
To use your own Set of Data, just replace all files with your one or more .txt files in ./bigDataForSkt/cnv. 
All Data from multiple files will be treated as One Big File for Final analysis.


####Prereq
Install Java 8 or above and make sure the PATH is updated
Install Spark.
preferably install it in C:\Spark in Windows or /Spark in Unix etc
Download winutils.exe binary from https://github.com/steveloughran/winutils repository for windows only and place it in C:\Spark\bin

Refer to:
https://jaceklaskowski.gitbooks.io/mastering-apache-spark/spark-tips-and-tricks-running-spark-windows.html

Download jar from:
https://github.com/eGangotri/eGangotriInScala/tree/master/bigDataForSkt
if using sbt make sure 
mainClass in Compile := Some("org.bigdata.skt.WordCount")
is uncommented in build.sbt

java -jar bigDataForSkt.jar
OR
sbt run

Note 1:
If Spark is installed in a Folder other than the default of C:\Spark[Windows] or /Spark[Unix,Mac]
you will have to use the following format to inform the location of Spark

java -jar bigDataForSkt.jar <PATH>
OR
sbt "run <PATH>"

Example:
java -jar bigDataForSkt.jar "/Program Files/Spark"
OR
sbt "run C:/Program Files/Spark"

Developers using sbt should have the values "run <PATH>" must be in Double Quotes.
For Windows Users the Path to Spark will have a bin folder which must have winutils.exe placed in Windows.

Note 2:
The First time when you run the Jar,  the following Folder will be created:
./bigDataForSkt/cnv

This Folder will have as a sample the Complete Works of Abhinavagupta as a Digital Corpus available at Muktabodha.

In order to work with your own files simply dump your Unicode-compatible Texts as .txt to:
./bigDataForSkt/cnv

All Files will be treated as one Single File for the generation of the Word Count.





