package example.spark.kafka

/**
  * title           : Initializer
  * description     : This object treats main rules of aplication
  * author          : Jessiane Andrade
  * email           : syane_andrade@outlook.com
  * date            : 20190520
  * usage           :
  * notes           : Kafka topics and ScyllaDB need to be configured in config.MetaInf
  * */


import com.datastax.spark.connector.cql.CassandraConnectorConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.cassandra._
import org.apache.spark.sql.streaming.OutputMode
import example.spark.kafka.config.MetaInf

object Initializer extends App {

  val spark = SparkSession.builder
    .appName("SparkKafkaScylla")
    .getOrCreate()
    .setCassandraConf("scylla",
      Map(CassandraConnectorConf.ConnectionHostParam.name -> MetaInf.scyllaCluster))


  val sparkReaderStream = spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", MetaInf.kafkaEnd)
    .option("subscribe", MetaInf.kafkaTopic)
    .load()
    .selectExpr("CAST(key AS STRING) AS symbol",
      "CAST(value AS STRING) AS data",
      "CAST(timestamp AS TIMESTAMP) AS day")
    .select("data", "day")
    .writeStream
//    .format("console")
    .format("example.spark.kafka.scyllaDB.ScyllaSinkProvider")
    .outputMode(OutputMode.Append)
    .options(Map(
      "cluster" -> MetaInf.scyllaCluster,
      "keyspace" -> MetaInf.scyllakeyspace,
      "table" -> MetaInf.scyllaNameTable,
      "checkpointLocation" -> "/tmp/checkpoints"
    )
    )
    .start()

  sparkReaderStream.awaitTermination()
}
