package com.omd.spark.practice

// For implicit conversions like converting RDDs to DataFrames


object SQLExample extends SparkTools with IOs {
  def main(args: Array[String]): Unit = {
    withFile(args) { file ⇒
      withSession("SQL basic Example") { spark ⇒
        import spark.implicits._

        val df = spark.read.json(path = file)
        df.printSchema()

        df.select("name").show()
        df.select($"name", $"age" + 1).show()
        df.groupBy("age").count().show()

        // Displays the content of the DataFrame to stdout
        df.show()
      }
    }
  }
}