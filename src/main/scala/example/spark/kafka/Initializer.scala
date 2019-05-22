package example.spark.kafka

/**
  * title           : Initializer
  * description     :
  * author          : Jessiane Andrade
  * email           : syane_andrade@outlook.com
  * date            : 20190520
  * usage           :
  * notes           :
  * */

import com.datastax.spark.connector._
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, functions => f}
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.sql.execution.streaming.Sink
import org.apache.spark.sql.sources.StreamSinkProvider
import org.apache.spark.sql.cassandra._
import com.datastax.spark.connector.cql.CassandraConnectorConf
import org.joda.time.DateTime




object Initializer extends App {

  val spark = SparkSession.builder
    .appName("SparkKafkaScylla")
    .getOrCreate()
    .setCassandraConf("scylla",
      Map(CassandraConnectorConf.ConnectionHostParam.name -> "Teste Cluster"))

  val sparkReaderStream = spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "CLIENTE")
    .load()
    .selectExpr("CAST(key AS STRING) AS symbol",
      "CAST(value AS STRING) AS data",
      "CAST(timestamp AS TIMESTAMP) AS day")
    .select("data", "day")
    .writeStream
    .outputMode(OutputMode.Append)
    .format("console")
    .start()

  sparkReaderStream.awaitTermination()
}
