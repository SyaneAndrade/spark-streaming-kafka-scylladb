package example.spark.kafka.config

/**
  * title           : MetaInf
  * description     : This object have configuration of server of production and homologation
  * author          : Jessiane Andrade
  * email           : syane_andrade@outlook.com
  * date            : 20190522
  * usage           :
  * notes           :
  * */

object MetaInf {
  //Configuracao para consumo do topico do kafka
  val kafkaEnd = "localhost:9092"
  val kafkaTopic = "CLIENTE"

  //Configuracao para conexao do ScyllaDB
  val scyllakeyspace = "timeline"
  val scyllaNameTable = "event"
  val scyllaCluster = "Test Cluster"

}
