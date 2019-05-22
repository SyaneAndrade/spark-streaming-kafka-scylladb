name := "spark-streaming-kafka-scylladb"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.3.0"


evictionWarningOptions in update := EvictionWarningOptions.default.withWarnTransitiveEvictions(false)


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "2.2.0",
  "org.apache.spark" %% "spark-streaming" % "2.2.0",
//  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.2.0",
  "com.datastax.spark" %% "spark-cassandra-connector" % sparkVersion
)