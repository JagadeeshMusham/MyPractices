#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Apr 24 09:09:42 2020

@author: mvenkata
"""

import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from os import listdir
from os.path import isfile, join
def get_df(basepath):
    all_files=listdir(basepath)
    li = []
    for filename in all_files:
        print(filename)
        df = pd.read_csv(basepath+filename, index_col=None, header=0)
        li.append(df)
    return pd.concat(li, axis=0, ignore_index=True)

def get_df_from_parquet(basepath):
    all_files=listdir(basepath)
    li = []
    for filename in all_files:
        df = pd.read_parquet(basepath+filename, engine='pyarrow')
        li.append(df)
    return pd.concat(li, axis=0, ignore_index=True)

def print_summary(df,column):
    print("%i unique %s from dataset:"%(len(df[column].unique()),column))
    print(df[column].unique())
    return

'''dfAll=get_df("/Users/mvenkata/Downloads/FaultData/TrainDataset_Full/")
dfLab=get_df("/Users/mvenkata/Downloads/FaultData/LabeledDataset_Full/")
dfLab=dfAll.loc[(~dfAll['lastComment'].isnull()) | (~dfAll['serviceId'].isnull())]
dfLab.shape
dfAll.shape
dfLab=dfAll[(dfAll['lastComment'] != None)]
dfLab.shape'''

    
'''dfAll['eventType'].plot.hist()
dfAll['probableCause'].plot.hist()
dfAll['priority'].plot.hist()
dfAll['classification'].plot.hist()
dfAll['acknowledged'].plot.hist()

dfLab['eventType'].plot.hist()
dfLab['probableCause'].plot.hist(bins=20)
dfLab['priority'].plot.hist()
dfLab['classification'].plot.hist()
dfLab['acknowledged'].plot.hist()

dfAll.loc[dfAll['eventType'] == 2 ]['eventType'].plot.hist()'''

#Graphs for all Alarm Attributes (All alarms vs Labeled important vs Labeled non-important)
    #1. perceivedSeverity
    #2. count
    #3. duration
    #4. eventType
    #5. probableCause
    #6. priority
    #7. classification
    #8. acknowledged
    #9. deferred
    #10. suppressed

#Graphs for SDN Data
dfAll_sdn = pd.read_parquet('/Users/hvarakal/Desktop/Comporium/comporium_data_non_transient_required_fields_with_labels.parquet', engine='pyarrow')
dfAll_sdn.shape
#dfAll_sdn['perceivedSeverity'].plot.hist()
#dfAll_sdn['perceivedSeverity'].value_counts()
dfLabI_sdn = dfAll_sdn.loc[(dfAll_sdn['labels'] == 1)]
dfLabI_sdn.shape
dfLabNI_sdn = dfAll_sdn.loc[(dfAll_sdn['labels'] == 0)]
dfLabNI_sdn.shape
dfAll_sdn['labels'].value_counts()

dfAll_sdn['perceivedSeverity'].plot.hist()
dfLabI_sdn['perceivedSeverity'].plot.hist()
dfLabNI_sdn['perceivedSeverity'].plot.hist()
dfAll_sdn['perceivedSeverity'].value_counts()
dfLabI_sdn['perceivedSeverity'].value_counts()
dfLabNI_sdn['perceivedSeverity'].value_counts()

dfLabI_sdn.loc[dfLabI_sdn['perceivedSeverity']>2]['perceivedSeverity'].plot.hist()
dfLabNI_sdn.loc[dfLabNI_sdn['perceivedSeverity']>2]['perceivedSeverity'].plot.hist()

dfLabI_sdn_gt2sever = dfLabI_sdn.loc[dfLabI_sdn['perceivedSeverity']>5]
dfLabNI_sdn_gt2sever = dfLabNI_sdn.loc[dfLabNI_sdn['perceivedSeverity']>5]

dfAll_sdn['duration'].plot.hist(bins=10)
dfLabI_sdn['duration'].plot.hist(bins=range(0,1000))
dfLabNI_sdn['duration'].plot.hist(bins=10)
dfAll_sdn['duration'].value_counts()
dfLabI_sdn['duration'].value_counts()
dfLabNI_sdn['duration'].value_counts()

dfLabI_sdn.loc[dfLabI_sdn['duration']<5000]['duration'].plot.hist(bins=60)
dfLabNI_sdn.loc[dfLabNI_sdn['duration']<5000]['duration'].plot.hist(bins=60)

dfLabI_sdn_le5000dur = dfLabI_sdn.loc[dfLabI_sdn['duration']<5000]
dfLabNI_sdn_le5000dur = dfLabNI_sdn.loc[dfLabNI_sdn['duration']<5000]



dfAll_sdn['count'].plot.hist(bins=10)
dfLabI_sdn['count'].plot.hist(bins=range(0,50))
dfLabNI_sdn['count'].plot.hist(bins=range(0,50))
dfAll_sdn['count'].value_counts()
dfLabI_sdn['count'].value_counts()
dfLabNI_sdn['count'].value_counts()

dfLabI_sdn.loc[dfLabI_sdn['count']>0]['count'].plot.hist(bins=range(0,50))
dfLabNI_sdn.loc[dfLabNI_sdn['count']>1]['count'].plot.hist(bins=range(0,50))


dfAll_sdn['eventType'].plot.hist()
dfLabI_sdn['eventType'].plot.hist()
dfLabNI_sdn['eventType'].plot.hist()
dfAll_sdn['eventType'].value_counts()
dfLabI_sdn['eventType'].value_counts()
dfLabNI_sdn['eventType'].value_counts()



dfAll_sdn['probableCause'].plot.hist()
dfLabI_sdn['probableCause'].plot.hist()
dfLabNI_sdn['probableCause'].plot.hist()
dfAll_sdn['probableCause'].value_counts()
dfLabI_sdn['probableCause'].value_counts()
dfLabNI_sdn['probableCause'].value_counts()


dfAll_sdn['priority'].plot.hist()
dfLabI_sdn['priority'].plot.hist()
dfLabNI_sdn['priority'].plot.hist()
dfAll_sdn['priority'].value_counts()
dfLabI_sdn['priority'].value_counts()
dfLabNI_sdn['priority'].value_counts()

dfLabI_sdn.loc[dfLabI_sdn['priority']>=1]['priority'].plot.hist(range(0,3))
dfLabNI_sdn.loc[dfLabNI_sdn['priority']>=1]['priority'].plot.hist()


dfAll_sdn['acknowledged'].plot.hist()
dfLabI_sdn['acknowledged'].astype(float).plot.hist()
dfLabNI_sdn['acknowledged'].astype(float).plot.hist()
dfAll_sdn['acknowledged'].value_counts()
dfLabI_sdn['acknowledged'].value_counts()
dfLabNI_sdn['acknowledged'].value_counts()



dfAll_sdn['deferred'].plot.hist()
dfLabI_sdn['deferred'].astype(int).plot.hist()
dfLabNI_sdn['deferred'].astype(int).plot.hist()
dfAll_sdn['deferred'].value_counts()
dfLabI_sdn['deferred'].value_counts()
dfLabNI_sdn['deferred'].value_counts()



dfAll_sdn['suppressed'].plot.hist()
dfLabI_sdn['suppressed'].astype(int).plot.hist()
dfLabNI_sdn['suppressed'].astype(int).plot.hist()
dfAll_sdn['suppressed'].value_counts()
dfLabI_sdn['suppressed'].value_counts()
dfLabNI_sdn['suppressed'].value_counts()



dfAll_sdn.columns


#Graphs for Comporium Data
# dfAll_comp = get_df_from_parquet('/Users/mvenkata/Downloads/FaultData/COMPORIUM_FM_3M/parquet_dataset_90d/comporium_parquet_90d/dataset/')
dfAll_comp = pd.read_parquet('/Users/hvarakal/Desktop/Comporium/comporium_data_non_transient_required_fields_with_labels.parquet', engine='pyarrow')
dfAll_comp = dfAll_comp.loc[(dfAll_comp['transitory'] == 0)]
dfAll_comp['labels'] = dfAll_comp.apply(lambda row: 1 if ( ( row['lastComment'] != None ) | ( row['serviceId'] != None ) ) else 0, axis=1)
dfAll_comp.shape

dfLabI_comp = dfAll_comp.loc[(dfAll_comp['labels'] == 1)]
dfLabI_comp.shape
dfLabNI_comp = dfAll_comp.loc[(dfAll_comp['labels'] == 0)]
dfLabNI_comp.shape
dfAll_comp['labels'].value_counts()

dfAll_comp['perceivedSeverity'].plot.hist()
dfLabI_comp['perceivedSeverity'].plot.hist()
dfLabNI_comp['perceivedSeverity'].plot.hist()
dfAll_comp['perceivedSeverity'].value_counts()
dfLabI_comp['perceivedSeverity'].value_counts()
dfLabNI_comp['perceivedSeverity'].value_counts()

dfLabI_comp.loc[dfLabI_comp['perceivedSeverity']>2]['perceivedSeverity'].plot.hist()
dfLabI_comp.loc[dfLabI_comp['perceivedSeverity']>2]['perceivedSeverity'].plot.hist()

dfLabI_comp.loc[dfLabI_comp['perceivedSeverity']>5]['perceivedSeverity'].plot.hist()
dfLabNI_comp.loc[dfLabNI_comp['perceivedSeverity']>5]['perceivedSeverity'].plot.hist()

dfLabI_comp_gt2sever = dfLabI_comp.loc[dfLabI_comp['perceivedSeverity']>5]
dfLabNI_comp_gt2sever = dfLabNI_comp.loc[dfLabNI_comp['perceivedSeverity']>5]


dfLabI_comp.loc[dfLabI_comp['duration']<5000]['duration'].plot.hist(bins=60)
dfLabNI_comp.loc[dfLabNI_comp['duration']<5000]['duration'].plot.hist(bins=60)


dfLabI_comp_le5000dur = dfLabI_comp.loc[dfLabI_comp['duration']<5000]
dfLabNI_comp_le5000dur = dfLabNI_comp.loc[dfLabNI_comp['duration']<5000]

dfAll_comp['priority'].plot.hist()
dfLabI_comp['priority'].plot.hist()
dfLabNI_comp['priority'].plot.hist()
dfAll_comp['priority'].value_counts()
dfLabI_comp['priority'].value_counts()
dfLabNI_comp['priority'].value_counts()


dfLabI_comp.loc[dfLabI_comp['priority']>=1]['priority'].plot.hist(range(0,3))
dfLabNI_comp.loc[dfLabNI_comp['priority']>=1]['priority'].plot.hist()


dfAll_comp['acknowledged'].plot.hist()
dfLabI_comp['acknowledged'].astype(float).plot.hist()
dfLabNI_comp['acknowledged'].astype(float).plot.hist()
dfAll_comp['acknowledged'].value_counts()
dfLabI_comp['acknowledged'].value_counts()
dfLabNI_comp['acknowledged'].value_counts()

dfAll_comp['deferred'].plot.hist()
dfLabI_comp['deferred'].astype(int).plot.hist()
dfLabNI_comp['deferred'].astype(int).plot.hist()
dfAll_comp['deferred'].value_counts()
dfLabI_comp['deferred'].value_counts()
dfLabNI_sdn['deferred'].value_counts()



dfAll_comp['suppressed'].plot.hist()
dfLabI_comp['suppressed'].astype(int).plot.hist()
dfLabNI_comp['suppressed'].astype(int).plot.hist()
dfAll_comp['suppressed'].value_counts()
dfLabI_comp['suppressed'].value_counts()
dfLabNI_comp['suppressed'].value_counts()

dfLabI_comp.loc[dfLabI_comp['count']>1]['count'].plot.hist(bins=range(0,50))
dfLabNI_comp.loc[dfLabNI_comp['count']>1]['count'].plot.hist(bins=range(0,50))



