name := "spark-streaming-kafka-scylladb"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.3.0"


evictionWarningOptions in update := EvictionWarningOptions.default.withWarnTransitiveEvictions(false)


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
  "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion,
  "com.datastax.spark" %% "spark-cassandra-connector" % sparkVersion
)