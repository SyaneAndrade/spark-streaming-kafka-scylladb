

name := "spark-streaming-kafka-scylladb"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.3.0"



evictionWarningOptions in update := EvictionWarningOptions.default.withWarnTransitiveEvictions(false)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

lazy val raiz = (project in file("."))
  .settings(mainClass in Compile := Some("example.spark.kafka.Initializer"))
libraryDependencies ++= Seq(
//  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % "2.2.0",
  "org.apache.spark" %% "spark-streaming" % "2.2.0",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.2.0",
  "com.datastax.spark" %% "spark-cassandra-connector" % sparkVersion,
  "com.databricks" %% "spark-avro" % "4.0.0"
)


/*
central.maven.org/maven2/org/apache/spark/spark-core_2.11/2.2.0/spark-core_2.11-2.2.0.jar
central.maven.org/maven2/org/apache/spark/spark-sql_2.11/2.0.0/spark-sql_2.11-2.0.0.jar
central.maven.org/maven2/org/apache/spark/spark-streaming_2.11/2.0.0/spark-streaming_2.11-2.0.0.jar
central.maven.org/maven2/org/apache/spark/spark-sql-kafka-0-10_2.11/2.2.0/spark-sql-kafka-0-10_2.11-2.2.0.jar
central.maven.org/maven2/com/datastax/spark/spark-cassandra-connector_2.11/2.3.0/spark-cassandra-connector_2.11-2.3.0.jar

 */