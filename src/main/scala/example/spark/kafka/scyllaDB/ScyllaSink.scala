package example.spark.kafka.scyllaDB

/**
  * title           : ScyllaSinkProvider
  * description     :
  * author          : Jessiane Andrade
  * email           : syane_andrade@outlook.com
  * date            : 20190520
  * usage           :
  * notes           :
  * */

import org.apache.spark.sql.execution.streaming.Sink
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.cassandra._
import org.apache.spark.sql.SaveMode


class ScyllaSink(parameters: Map[String, String]) extends Sink {
  override def addBatch(batchId: Long, data: DataFrame): Unit =
    data.write
      .cassandraFormat(parameters("table"),
        parameters("keyspace"),
        parameters("cluster"))
      .mode(SaveMode.Append)
      .save()

}
