#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon May 11 15:38:56 2020

@author: hvarakal
"""

import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from os import listdir
from os.path import isfile, join
import pickle
def get_df(basepath):
    all_files=listdir(basepath)
    li = []
    for filename in all_files:
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

#dfAll = pd.read_parquet('/Users/hvarakal/Desktop/Comporium/comporium_data_non_transient_required_fields_with_labels.parquet', engine='pyarrow')
#dfLabImp = dfAll.loc[(dfAll['labels'] == 1)]
#dfCheck = dfLabImp.loc[dfLabImp['perceivedSeverity']<=2]


#Graphs for Comporium Data
dfAll_comp = pd.read_parquet('/Users/hvarakal/Desktop/analysis/comporium_data_non_transient_required_fields_with_labels.parquet', engine='pyarrow')
# dfAll_comp = dfAll_comp.loc[(dfAll_comp['transitory'] == 0)]
# dfAll_comp['labels'] = dfAll_comp.apply(lambda row: 1 if ( ( row['lastComment'] != None ) | ( row['serviceId'] != None ) ) else 0, axis=1)
dfAll_comp.shape

dfLabI_comp = dfAll_comp.loc[(dfAll_comp['labels'] == 1)]
dfLabI_comp.shape
dfAll_comp['labels'].value_counts()

dfAll_comp['perceivedSeverity'].plot.hist()
dfLabI_comp['perceivedSeverity'].plot.hist()
dfAll_comp['perceivedSeverity'].value_counts()
dfLabI_comp['perceivedSeverity'].value_counts()


dfLabI_comp_let2sever = dfLabI_comp.loc[dfLabI_comp['perceivedSeverity']<=2]


# dfLabI_comp.loc[dfLabI_comp['count']>1]['count'].plot.hist(bins=range(0,50)) 
# dfAll_comp.shape dfAll_comp.columns 
# dfAll_comp['perceivedSeverity'].plot.hist()
# dfLabI_comp.loc[dfLabI_comp['perceivedSeverity']<=2]
# dfLabI_comp_let2sever = dfLabI_comp.loc[dfLabI_comp['perceivedSeverity']<=2]
# dfLabI_comp_let2sever
