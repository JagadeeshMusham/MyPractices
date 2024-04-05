from pyspark.sql import SparkSession
from pyspark import SparkContext
from pyspark.streaming import StreamingContext

from pyspark.sql.types import *

from pyspark.sql.functions import *

sc = SparkContext("spark://JMUSHAM-LVCJ:7077", appName="PythonSparkStreaming")
ssc = StreamingContext(sc, 2)

print("\t\ttest-2")
session = SparkSession.builder.getOrCreate()

df = session.readStream \
        .format("kafka") \
        .option("kafka.bootstrap.servers", "127.0.0.1:9092") \
        .option("subscribe", "test12") \
        .option("startingOffsets", "earliest") \
        .option("failOnDataLoss", "false") \
        .load()

print "Schema:"
df.printSchema()

personStringDF = df.selectExpr("CAST(value AS STRING)")

personStringDF.printSchema()

schema = StructType() \
        .add("a",IntegerType()) \
        .add("b",StringType()) \
        .add("c",BooleanType()) \
        .add("d",TimestampType())

personDF = personStringDF.select(from_json(col("value"), schema).alias("data")).select("data.*")

print "======"
personDF.printSchema()

print(personDF)
print "======"


#Following is to write data into Kafka
personDF.selectExpr("CAST(b AS STRING) AS key", "to_json(struct(*)) AS value") \
   .writeStream \
   .format("kafka") \
   .outputMode("append") \
   .option("kafka.bootstrap.servers", "127.0.0.1:9092") \
   .option("topic", "josn_data_topic1") \
   .option("checkpointLocation", "./checkpoint") \
   .trigger(continuous="5 second") \
   .start() \
   .awaitTermination()
   
print("-----")
'''
  
#Following is to write data at console
personDF.writeStream \
      .format("console") \
      .outputMode("append") \
      .start() \
      .awaitTermination()
'''      
print "just before stop"

session.stop()