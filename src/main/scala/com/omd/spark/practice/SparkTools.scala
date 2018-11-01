package com.omd.spark.practice

import org.apache.spark.sql.SparkSession

private[practice] trait SparkTools {
  final protected def withSession[A](name: String)(f: SparkSession ⇒ A):A = {
    val spark = SparkSession.builder.appName(name).master("spark://master:7077").getOrCreate()
    try {
      f(spark)
    } finally {
      spark.stop()
    }
  }
}
