package com.omd.spark.practice

import scalaz.syntax.std.boolean._

private[practice] trait IOs {
  final protected def withFile(from: Array[String])(f: String ⇒ Unit): Unit = file(from) match {
    case Some(file) ⇒ f(file)
    case None       ⇒ System.err.println(s"""file [${from.mkString(",")}] not found""")
  }

  private def file(from: Array[String]): Option[String] = (from.length > 0).option(from(0))

}
