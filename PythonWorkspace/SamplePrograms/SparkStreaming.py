#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Tue Apr 21 15:52:17 2020

@author: jmusham
"""

from pyspark.sql import SparkSession
from pyspark import SparkContext
from pyspark.streaming import StreamingContext

from pyspark.sql.types import *

from pyspark.sql.functions import *

import sys
import getopt

def main(argv):
    sparkConfigToken = ''
    kafkaConfigToken = ''
    
    # reading command line arguments
    try:
        sparkToken = ''
        opts, args = getopt.getopt(argv,"s:k:",["spark","kafka"])
        for opt, arg in opts:
            #spark argument
            if opt == "-s":
                sparkToken = arg
            #Kafka arguments
            elif opt == "-k":
                kafkaConfigToken = arg
            # wrong case
            else:
                print 'SparkStreaming.py -s <saprkIP:port> -k <kafkaIP:port>'
                sys.exit()
            
            #constructing spark token
            sparkConfigToken = "spark://" + sparkToken
            
    #Exception case while reading command line arguments
    except getopt.GetoptError:
        print 'SparkStreaming.py -s <saprkIP:port> -k <kafkaIP:port>'
        sys.exit(2)
    
    streamAlarms(sparkConfigToken, kafkaConfigToken)


def streamAlarms(sparkConfigToken, kafkaConfigToken):
    # printing the received command line arguments
    print 'Spark setting is ', sparkConfigToken
    print 'Kafka setting is ', kafkaConfigToken

    #Create Spark Context
    sc = SparkContext(sparkConfigToken, appName="Alarm Anomaly Detection")
    ssc = StreamingContext(sc, 2)
    
    #create spark session
    session = SparkSession.builder.getOrCreate()

    #reading the stream data
    df = session.readStream \
        .format("kafka") \
        .option("kafka.bootstrap.servers", kafkaConfigToken) \
        .option("subscribe", "ad") \
        .option("startingOffsets", "earliest") \
        .option("failOnDataLoss", "false") \
        .load()
    
    alarmStringDF = df.selectExpr("CAST(value AS STRING)")

    # creating the structure type
    schema = StructType() \
                .add("sequenceNumber",IntegerType()) \
                .add("raisedTime",TimestampType()) \
                .add("receivedTime",TimestampType()) \
                .add("lastModifiedTime",TimestampType()) \
                .add("eventType",StringType()) \
                .add("perceivedSeverity",IntegerType()) \
                .add("specificProblem",StringType()) \
                .add("count",IntegerType()) \
                .add("transitory",BooleanType()) \
                .add("duration",IntegerType()) \
                .add("serviceAffecting",BooleanType()) \
                .add("serviceId",StringType()) \
                .add("lastComment",StringType()) \
                .add("classification",StringType()) \
                .add("flapping",BooleanType())
                
                
                
    #converting to expected structure format
    alarmDF = alarmStringDF.select(from_json(col("value"), schema).alias("data")).select("data.*")
    
    
    #to write received alarm to Kafka topic
    writeAlarmToKafka(alarmDF, kafkaConfigToken)
    
    '''
    # to write received alarm on console
    writeAlarmsOnConsole(alarmDF)
    '''

    # to stop the spark session
    session.stop()
    
#Following is to write data into Kafka
def writeAlarmToKafka(alarmDF, kafkaConfigToken):
    alarmDF.selectExpr("CAST(specificProblem AS STRING) AS key", "to_json(struct(*)) AS value") \
        .writeStream \
        .format("kafka") \
        .outputMode("append") \
        .option("kafka.bootstrap.servers", kafkaConfigToken) \
        .option("topic", "ado") \
        .option("checkpointLocation", "./checkpoint") \
        .trigger(continuous="5 second") \
        .start() \
        .awaitTermination()
   
def writeAlarmsOnConsole(alarmDF):
    #Following is to write data at console
    alarmDF.writeStream \
        .format("console") \
        .outputMode("append") \
        .trigger(continuous="5 second") \
        .start() \
        .awaitTermination()


if __name__ == "__main__":
   main(sys.argv[1:])
