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

rm -rf data/*
rm -rf archives/*

sbt clean compile assembly

cp target/scala-2.11/first-sample.jar spark/archives/
cp ${SPARK_HOME}/README.md spark/data/

${SPARK_HOME}/bin/spark-submit \
  --class "com.omd.spark.practice.FirstApp" \
  --master spark://localhost:7077 \
  --deploy-mode cluster \
  /tmp/archives/first-sample.jar \
  "/tmp/data/README.md"