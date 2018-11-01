package com.omd.spark.practice

import org.apache.spark.sql.SparkSession
import scalaz.syntax.std.boolean._

object FirstApp {

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

  private def withSession[A](name: String)(f: SparkSession ⇒ A):A = {
    val spark = SparkSession.builder.appName(name).getOrCreate()
    try {
      f(spark)
    } finally {
      spark.stop()
    }
  }

  private def withFile(from: Array[String])(f: String ⇒ Unit): Unit = file(from) match {
    case Some(file) ⇒ f(file)
    case None       ⇒ System.err.println(s"""file $from not found""")
  }

  private def file(from: Array[String]): Option[String] = (from.length > 0).option(from(0))
}
