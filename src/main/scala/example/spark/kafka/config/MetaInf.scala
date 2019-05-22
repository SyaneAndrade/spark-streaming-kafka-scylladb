package example.spark.kafka.config

object MetaInf {
  //Configuracao para consumo do topico do kafka
  val kafkaEnd = "localhost:9092"
  val kafkaTopic = "CLIENTE"

  //Configuracao para conexao do ScyllaDB
  val scyllakeyspace = "example_spark"
  val scyllaNameTable = "dadosclient"
  val scyllaCluster = "Test Cluster"

}
