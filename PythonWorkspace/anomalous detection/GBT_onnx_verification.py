#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Apr 21 17:05:07 2020

@author: mvenkata
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

is_sdn = False

if is_sdn:
    dfAll = pd.read_parquet('/Users/jmusham/eclipse-workspace/PythonWorkspace/anomalous detection/dfAll_non_transient_with_labels.parquet', engine='pyarrow')
else:
    dfAll = pd.read_parquet('/Users/jmusham/eclipse-workspace/PythonWorkspace/anomalous detection/comporium_data_non_transient_required_fields_with_labels.parquet', engine='pyarrow')
    
print("\n*** GradientBoostingClassifier (Supervised ML) *** \n")

#dfAll['labels']=labels

dfTrain=dfAll[['perceivedSeverity','count','duration','labels']]
dfTrain=dfTrain.dropna(how='any')


#X=dfTrain[['perceivedSeverity','count','duration']].to_numpy()
X=dfTrain[['perceivedSeverity','count','duration']].to_numpy()
true_labels=dfTrain[['labels']].to_numpy().ravel()

#clean-up
nX=len(X)
i=0
while i<nX:
    j=0
    nJ=len(X[i])
    while j<nJ:
        if isinstance(X[i][j],str):
            s=X[i][j]
            try:
                X[i][j]=float(s)
            except ValueError:
                v=float(s.split(' ')[0])
                X[i][j]=v
                print("%s --> %f"%(s,v))
        j+=1
    i+=1
    pass

print(X.shape)
print(true_labels.shape)

#Better: randomized split between train vs test
from sklearn.model_selection import ShuffleSplit
ss = ShuffleSplit(n_splits=1, test_size=0.2, random_state=0)
for train_indices, test_indices in ss.split(X):
    pass

X_train=[]
true_labels_train=[]
for i in train_indices:
    X_train.append(X[i])
    true_labels_train.append(true_labels[i])
    
X_test=[]
true_labels_test=[]
for i in test_indices:
    X_test.append(X[i])
    true_labels_test.append(true_labels[i])


#Train Supervised ML classifier
from sklearn import ensemble
clf = ensemble.GradientBoostingClassifier()
clf.fit(X_train,true_labels_train)

sdn_model = None
comporium_model = None
if is_sdn:
    sdn_model = clf
    onnx_file = "model_sdn.onnx"
else:
    comporium_model = clf
    onnx_file = "model_comp.onnx"

#Persist model in ONNX
#Read te model and 
#Test classifier
inferred_labels_test=clf.predict(X_test)

#Evaluate precision, comparing inferred_labels to true_labels
from sklearn import metrics
print(metrics.classification_report(true_labels_test,inferred_labels_test,target_names=["noise alarm","important alarm"]))
print(metrics.confusion_matrix(true_labels_test,inferred_labels_test))
nBefore=len(inferred_labels_test)
nAfter=inferred_labels_test.sum()
print("Reduction in number of alarms: %i --> %i (%.1f%%)"%(nBefore,nAfter,100*(nBefore-nAfter)/nBefore))


#ONNX
# Convert into ONNX format
from skl2onnx import convert_sklearn
from skl2onnx.common.data_types import FloatTensorType
initial_type = [('float_input', FloatTensorType([None,3]))]
onx = convert_sklearn(clf, initial_types=initial_type)
with open(onnx_file, "wb") as f:
    f.write(onx.SerializeToString())
    
# Compute the prediction with ONNX Runtime
import onnxruntime as rt
import numpy
sess = rt.InferenceSession(onnx_file)
input_name = sess.get_inputs()[0].name
label_name = sess.get_outputs()[0].name
pred_onnx = sess.run([label_name], {input_name: X_test})[0]


from sklearn import metrics
print(metrics.classification_report(true_labels_test,pred_onnx,target_names=["noise alarm","important alarm"]))
print(metrics.confusion_matrix(true_labels_test,pred_onnx))
nBefore=len(pred_onnx)
nAfter=pred_onnx.sum()
print("ONNXRuntime Results: Reduction in number of alarms: %i --> %i (%.1f%%)"%(nBefore,nAfter,100*(nBefore-nAfter)/nBefore))
