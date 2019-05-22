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

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.sources.StreamSinkProvider
import org.apache.spark.sql.streaming.OutputMode


class ScyllaSinkProvider extends StreamSinkProvider {
  override def createSink(sqlContext: SQLContext,
                          parameters: Map[String, String],
                          partitionColumns: Seq[String],
                          outputMode: OutputMode): ScyllaSink =
    new ScyllaSink(parameters)
}
