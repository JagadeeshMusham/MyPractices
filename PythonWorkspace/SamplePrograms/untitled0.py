#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Wed Apr  8 12:32:40 2020

@author: jmusham
"""

sc = SparkContext("spark://172.31.8.24:7077", appName="AnomalyDetectionSparkStreaming")
ssc = StreamingContext(sc, 2)
brokers, topic = sys.argv[1:]
alarms_stream = KafkaUtils.createDirectStream(ssc, [topic],{"metadata.broker.list": brokers})