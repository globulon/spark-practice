#!/usr/bin/env bash

sbt clean compile assembly

${SPARK_HOME}/bin/spark-submit \
  --class "com.omd.spark.practice.FirstApp" \
  --master local[4] \
  /home/omadas/dev/projects/scala/spark-practice/target/scala-2.11/first-sample.jar \
  "/home/omadas/dev/env/spark/README.md"