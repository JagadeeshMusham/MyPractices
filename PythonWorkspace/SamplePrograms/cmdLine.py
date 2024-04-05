#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Tue Apr 21 12:29:44 2020

@author: jmusham
"""
import sys
import getopt

def main(argv):
   sparkToken = ''
   kafkaToken = ''
   try:
       opts, args = getopt.getopt(argv,"s:k:",["spark","kafka"])
       
       for opt, arg in opts:
          if opt == "-s":
             sparkToken = arg
          elif opt == "-k":
             kafkaToken = arg
          else:
             print "inside else"
             print 'test.py -s <saprkIP:port> -o <kafkaIP:port>'
             sys.exit()
       sToken = "spark://"+sparkToken
       print 'Spark setting is ', sToken
       print 'Kafka setting is ', kafkaToken
   except getopt.GetoptError:
      print "inside exception"
      print 'test.py -i <inputfile> -o <outputfile>'
      sys.exit(2)

if __name__ == "__main__":
   main(sys.argv[1:])