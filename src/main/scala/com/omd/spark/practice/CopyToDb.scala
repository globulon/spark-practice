package com.omd.spark.practice

import java.util.Properties

import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.SaveMode.Overwrite
import org.apache.spark.sql.functions.concat
import org.apache.spark.sql.functions.lit


object CopyToDb extends IOs with SparkTools {
  def main(args: Array[String]): Unit = withFile(args) { file ⇒
    withSession(name = "CSV To Db") { spark ⇒
      import spark.implicits._

      spark.read.format("csv")
        .option("header", "true")
        .load(file)
        .select($"fname", $"lname", concat($"fname", lit(" "), $"lname") as "name")
        .write
        .mode(Overwrite)
        .jdbc(dbConnURL, table = "authors", prop)
    }
  }

  private def dbConnURL ="jdbc:postgresql://db/postgres"

  private def prop: Properties = {
    val prop = new Properties() //#E
    prop.setProperty("driver", "org.postgresql.Driver")
    prop.setProperty("user", "postgres")
    prop.setProperty("password", "mysecretpassword")
    prop
  }
}
