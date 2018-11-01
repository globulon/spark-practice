#!/usr/bin/env bash

if [ ! -d spark ]; then
  mkdir spark
fi

if [ ! -d spark/data ]; then
  mkdir spark/data
fi

if [ ! -d spark/archives ]; then
  mkdir spark/archives
fi

rm -rf spark/archives/*

sbt clean compile assembly

cp target/scala-2.11/demo.jar spark/archives/

${SPARK_HOME}/bin/spark-submit \
  --class "com.omd.spark.practice.$1" \
  --master spark://localhost:7077 \
  --deploy-mode cluster \
  /tmp/archives/demo.jar \
  "/tmp/data/$2"