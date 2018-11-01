package com.omd.spark.practice

object FirstApp extends SparkTools with IOs {

  def main(args: Array[String]): Unit = {
    withFile(args) { file ⇒
      withSession("First Application") { spark ⇒
        val logData = spark.read.textFile(file).cache()
        val numAs = logData.filter(_.contains("a")).count()
        val numBs = logData.filter(_.contains("b")).count()
        println(s"Lines with a: $numAs, Lines with b: $numBs")
      }
    }
  }

}
