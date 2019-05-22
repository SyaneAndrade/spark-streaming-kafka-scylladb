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


import com.datastax.spark.connector.cql.CassandraConnectorConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.cassandra._
import org.apache.spark.sql.streaming.OutputMode




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
//    .format("console")
    .format("example.spark.kafka.scyllaDB.ScyllaSinkProvider")
    .outputMode(OutputMode.Append)
    .options(Map(
      "cluster" -> "Test Cluster",
      "keyspace" -> "example_spark",
      "table" -> "dadosclient",
      "checkpointLocation" -> "/tmp/checkpoints"
    )
    )
    .start()

  sparkReaderStream.awaitTermination()
}
