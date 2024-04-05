#!/usr/bin/env python2
# -*- coding: utf-8 -*-

#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Feb  5 08:35:07 2020


SDN
labelled: 12k
tot events: 70M

Comporium
labelled: 150
total: 10M

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

load_from_file=False

is_sdn = False

train_new_model=False
model_path = 'affprop_event.pkl'

if load_from_file:
    #Read all parquet files
    #dfAll = get_df_from_parquet('/Users/mvenkata/Downloads/FaultData/SDN_FM_1Y/sdn_parquet_1y/dataset/')
    if not is_sdn:
        dfAll = get_df_from_parquet('/Users/mvenkata/Downloads/FaultData/COMPORIUM_FM_3M/parquet_dataset_90d/comporium_parquet_90d/dataset/')
    else:
        dfAll = get_df_from_parquet('/Users/mvenkata/Downloads/FaultData/SDN_FM_1Y/sdn_parquet_1y/dataset/')
else:
    #Read single parquet file
    if is_sdn:
        dfAll = pd.read_parquet('/Users/mvenkata/Downloads/FaultData/SDN_FM_1Y/sdn_parquet_1y/dataset_1.parquet/part-00000-8946a71b-235d-4a73-8733-950d7a3297aa-c000.snappy.parquet', engine='pyarrow')
    else:
        dfAll = pd.read_parquet('/Users/jmusham/eclipse-workspace/PythonWorkspace/anomalous detection/comporium_data_non_transient_required_fields_with_labels.parquet', engine='pyarrow')
        
dfAll.shape
dfNonTransient = dfAll.loc[(dfAll['transitory'] == 0)]
dfAll = dfNonTransient
dfLab=dfAll.loc[(~dfAll['lastComment'].isnull()) | (~dfAll['serviceId'].isnull())]
dfNonTransient.shape
dfLab.shape

'''

dfAll['test'] = dfAll.apply(lambda row: 1 if ( ( row['lastComment'] != None ) | ( row['serviceId'] != None ) ) else 0, axis=1)

dfAll['test1'] = dfAll.apply(lambda row: 1 if ( ( isinstance(row['lastComment'],str) ) | ( isinstance(row['lastComment'],str) ) )

dfAll['test'].value_counts()

dfAll['test1'].value_counts()

dfLab=dfAll.loc[(~dfAll['lastComment'].isnull()) | (~dfAll['serviceId'].isnull())]

dfLab.shape

'''

#dfLab=dfNonTransient.loc[(~dfNonTransient['lastComment'].isnull()) | (~dfNonTransient['serviceId'].isnull())]


'''#drop rows with 'specificProblem' is Nan
dfAll = dfAll[dfAll['specificProblem'].notna()]
dfAll.reset_index(drop=True, inplace=True)'''

'''print("dfAll (%i rows, %i cols)"%(dfAll.shape[0],dfAll.shape[1]))

print_summary(dfAll,'networkElementName')
print("\n")
print_summary(dfAll,'sourceName')
print("\n")
print_summary(dfAll,'specificProblem')
print("\n")'''

#Without specificProbelm
'''

## Calcuating Levenshtein Similarrity and Train Affinity Propagation Model

unique_events = dfAll['specificProblem'].unique()
unique_events = unique_events.astype(str)

if train_new_model:

    from Levenshtein import distance as L_distance
    import sklearn.cluster
    import distance
    import time
    import pickle

    t = time.time()
    lev_similarity = -1*np.array([[L_distance(w1,w2) for w1 in unique_events] for w2 in unique_events])
    elapsed = time.time() - t
    print("The time used for calculating the Levenshtein Similarity matric of the strings (" + str(len(unique_events)) + ") is:" + str(elapsed) + "seconds")


    t = time.time()
    affprop = sklearn.cluster.AffinityPropagation(affinity="precomputed", damping=0.5)
    affprop.fit(lev_similarity)
    elapsed = time.time() - t
    print("The time used for fitting the Levenshtein Similarity matric is:" + str(elapsed) + "seconds")


    with open(model_path, 'wb') as fid:
        pickle.dump(affprop, fid) 

else:
    with open(model_path, 'rb') as fid:
        affprop = pickle.load(fid)


## Processing the event description
CH = []

def sim_list(target,source):
    temp = [target if target in x else x for x in source]
    return list( dict.fromkeys(temp))

for cluster_id in np.unique(affprop.labels_):
    exemplar = unique_events[affprop.cluster_centers_indices_[cluster_id]]
    cluster = np.unique(unique_events[np.nonzero(affprop.labels_==cluster_id)])
    cluster_str = ", ".join(cluster)
    #print(" - *%s:* %s" % (exemplar, cluster_str))
    #print(exemplar)
    CH.append(exemplar)

## additional manual processing steps
CH_shrink = sim_list('IpsSignatureTriggered',CH)
CH_shrink = sim_list('dosHostDetectionAlert',CH_shrink)



# add new column in dfAll
dfAll['specificProblem_index'] = ''

# update the new column according to CH_shrink
for ch in CH_shrink:
    index = dfAll[dfAll['specificProblem'].str.contains(ch, regex=False)].index.tolist()
    dfAll.at[index, 'specificProblem_index'] = CH_shrink.index(ch)

# Sometimes the AP alrgorithm does not act as expected, need a iteration
CH_append = dfAll[dfAll['specificProblem_index'] == '']['specificProblem'].unique().tolist()
CH_shrink = CH_shrink + CH_append

# Reduce unecessary duplication
from collections import OrderedDict
CH_shrink = list(OrderedDict.fromkeys(CH_shrink)) 

#additional check
for ch in CH_append:
    index = dfAll[dfAll['specificProblem'].str.contains(ch, regex=False)].index.tolist()
    dfAll.at[index, 'specificProblem_index'] = CH_shrink.index(ch)

print("After processing, there are", str(len(CH_shrink)), "types of events recorded in the dataset.")


'''

'''
print("\n*** Computing labels *** \n")
nTic=[0,0,0,0,0] #nLabels,true_pos,false_pos,true_neg,false_neg
nSvc=[0,0,0,0,0]
nCom=[0,0,0,0,0]
nComSvc=[0,0,0,0,0]
labels=[]
i=0
dTmp=dfAll[['lastComment','serviceId']]
dTmp.shape

while i < 10:
    hasComment=isinstance(dTmp['lastComment'][i],str)
    hasTicket=False
    if hasComment:
        hasTicket=dTmp['lastComment'][i].count('Ticket')>0
    hasService=isinstance(dTmp['serviceId'][i],str)
    print dTmp['lastComment'][i]
    if hasTicket:
        nTic[0]+=1
    if hasService:
        nSvc[0]+=1
    if hasComment:
        nCom[0]+=1
    print hasComment
    print hasService
    if hasService or hasComment:
        nComSvc[0]+=1
        labels.append(1)
    else:
        labels.append(0)
        
    i+=1
    pass

print labels

###############################

'''

print("\n*** Computing labels *** \n")
dfAll['labels'] = dfAll.apply(lambda row: 1 if ( ( row['lastComment'] != None ) | ( row['serviceId'] != None ) ) else 0, axis=1)
#dfAll['labels'] = dfAll.apply(lambda row: 1 if ( ( isinstance(row['lastComment'],str) ) | ( isinstance(row['serviceId'],str) ) )

#SDN - Load updated labeled data from parquet
###dfAll = pd.read_parquet('/Users/mvenkata/Downloads/FaultData/SDN_FM_1Y/dfAll_non_transient_with_labels.parquet', engine='pyarrow')
if is_sdn:
    dfAll = pd.read_parquet('/Users/mvenkata/Downloads/FaultData/SDN_FM_1Y/dfAll_non_transient_with_labels.parquet', engine='pyarrow')


dfAll['labels'].value_counts()


dfAll.shape

print("\n*** GradientBoostingClassifier (Supervised ML) *** \n")

#dfAll['labels']=labels

dfTrain=dfAll[['perceivedSeverity','count','duration', 'priority', 'labels']]
dfTrain=dfTrain.dropna(how='any')


#X=dfTrain[['perceivedSeverity','count','duration']].to_numpy()
X=dfTrain[['perceivedSeverity','count','duration','priority']].to_numpy()
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

#Basic split between train vs test
"""
pivot=int(nX*0.8)
X_train=X[0:pivot]
true_labels_train=true_labels[0:pivot]
X_test=X[pivot:nX]
true_labels_test=true_labels[pivot:nX]
"""

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
else:
    comporium_model = clf

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

#Precision-Recall
proba = clf.predict_proba(X_test)
prec, rec, thresh = metrics.precision_recall_curve(true_labels_test, proba[:,1], pos_label=None, sample_weight=None)

print("test111")

#print(rec.tolist())
print(thresh.tolist()[20])


fig, ax = plt.subplots()
ax.plot(rec, prec, '.')
ax.set(xlabel='Recall', ylabel='Precision', title='Precision vs Recall from X_test')
fig.savefig("PrecisionRecall_all.png")
plt.show()

#automated cross-validation
#from sklearn.model_selection import cross_val_score
#precisions = cross_val_score(clf, X, true_labels, cv=10, scoring='precision')
#print("Precision: %.2f +/- %.2f"%(precisions.mean(),precisions.std()))
#recalls = cross_val_score(clf, X, true_labels, cv=10, scoring='recall')
#print("Recall: %.2f +/- %.2f"%(recalls.mean(),recalls.std()))

#Zoom on the >99% recall
#ir=[5,10,15,20,25,30,35,40,45,50,55,60,65,70]
#ir=[0,1,2,3,4,5,6]
ir=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
ir=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50]

i=1
ir=[]
while i <= 1465:
    ir.append(i)
    i = i + 1
    
#ir=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
ir=[0,1,2,3,4,5,6]
ir=[1,2,3,4,5,6,7,8,9,10,11,12]
redux=[]
rec2=[]
prec2=[]
for i in ir:
    tt=thresh[i]
    #tt=0.04459785109680062
    #print tt
    classes = clf.predict_proba(X_test)[:,1]
    classes[classes>=tt] = 1
    classes[classes<tt] = 0
    #print classes
    rec2.append(rec[i])
    prec2.append(prec[i])
    redux.append(classes.sum()/len(classes))

fig, ax = plt.subplots()
ax.plot(rec2, redux, '.')
#ax.set(xlabel='Recall', ylabel='Fraction of alarms kept', title='Alarm Reduction vs Recall from X_test (Zoom on Recall > 99%)')
ax.set(xlabel='Recall', ylabel='Fraction of alarms kept', title='Alarm Reduction vs Recall from X_test(COMPORIUM)')
fig.savefig("ReductionRecall_Zoom99.png")
plt.show()

i=1
ir=[]

#Index = 16 COMPORIUM Recall > 99%
#Index = 69 COMPORIUM Recall > 95%
while i <= 69:
    ir.append(i)
    i = i + 1
    
#ir=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
redux=[]
rec2=[]
prec2=[]
for i in ir:
    tt=thresh[i]
    #tt=0.04459785109680062  #COMPORIUM Recall > 99%
    #tt = 0.10824810556421087 #CMPORIUM Recall > 95%
    #print tt
    classes = clf.predict_proba(X_test)[:,1]
    classes[classes>=tt] = 1
    classes[classes<tt] = 0
    #print classes
    rec2.append(rec[i])
    prec2.append(prec[i])
    redux.append(1 - (classes.sum()/len(classes)))

fig, ax = plt.subplots()
ax.plot(rec2, redux, '.')
#ax.set(xlabel='Recall', ylabel='Fraction of alarms kept', title='Alarm Reduction vs Recall from X_test (Zoom on Recall > 99%)')
ax.set(xlabel='Recall', ylabel='Alarms Reduction', title='Alarm Reduction vs Recall from X_test(Zoom on Recall > 95%)')
fig.savefig("/Users/jmusham/eclipse-workspace/PythonWorkspace/anomalous detection/COMPORIUM_AlarmsReductionVsRecall_Recall_Gt_95per.png")
plt.show()


#Reports for threshold probability Recall = 99%, threshold = 0.045733831914779366(COMPORIUM)
threshold = 0.045733831914779366
inferred_labels_test_rec_gt_99 = clf.predict_proba(X_test)[:,1]
inferred_labels_test_rec_gt_99[inferred_labels_test_rec_gt_99>=threshold] = 1
inferred_labels_test_rec_gt_99[inferred_labels_test_rec_gt_99<threshold] = 0
from sklearn import metrics
print(metrics.classification_report(true_labels_test,inferred_labels_test_rec_gt_99,target_names=["noise alarm","important alarm"]))
print(metrics.confusion_matrix(true_labels_test,inferred_labels_test_rec_gt_99))
nBefore_new=len(inferred_labels_test_rec_gt_99)
nAfter_new=inferred_labels_test_rec_gt_99.sum()
print("Reduction in number of alarms: %i --> %i (%.1f%%)"%(nBefore_new,nAfter_new,100*(nBefore_new-nAfter_new)/nBefore_new))


i=1
ir=[]

#Index = 11 SDN Recall > 99%
#Index = 105 SDN Recall > 95%
#Total thresh length 1664
#while i <= 1663: #todo j
while i <= 1465:
    ir.append(i)
    i = i + 1
    
#ir=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
redux=[]
rec2=[]
prec2=[]
for i in ir:
    if (i % 50 == 0):
        print("\t\ti=  %d threshold value is: %s"%(i, thresh[i]))
    tt=thresh[i]
    #tt=0.04459785109680062  #COMPORIUM Recall > 99%
    #tt = 0.10824810556421087 #CMPORIUM Recall > 95%
    #print tt
    classes = clf.predict_proba(X_test)[:,1]
    classes[classes>=tt] = 1
    classes[classes<tt] = 0
    #print classes
    rec2.append(rec[i])
    prec2.append(prec[i])
    redux.append(1 - (classes.sum()/len(classes)))

fig, ax = plt.subplots()
ax.plot(rec2, redux, '.')
#ax.set(xlabel='Recall', ylabel='Fraction of alarms kept', title='Alarm Reduction vs Recall from X_test (Zoom on Recall > 99%)')
ax.set(xlabel='Recall', ylabel='Alarms Reduction', title='Alarm Reduction vs Recall from X_test')
fig.savefig("/Users/jmusham/eclipse-workspace/PythonWorkspace/anomalous detection/SDN_AlarmsReductionVsRecall_Full.png")
plt.show()


#Reports for threshold probability Recall = 99%, threshold = 0.021043011404930652(SDN)
#threshold = 0.021043011404930652
threshold = 0.0440483058649  #todo j
inferred_labels_test_rec_gt_99 = clf.predict_proba(X_test)[:,1]
inferred_labels_test_rec_gt_99[inferred_labels_test_rec_gt_99>=threshold] = 1
inferred_labels_test_rec_gt_99[inferred_labels_test_rec_gt_99<threshold] = 0
from sklearn import metrics
print(metrics.classification_report(true_labels_test,inferred_labels_test_rec_gt_99,target_names=["noise alarm","important alarm"]))
print(metrics.confusion_matrix(true_labels_test,inferred_labels_test_rec_gt_99))
nBefore_new=len(inferred_labels_test_rec_gt_99)
nAfter_new=inferred_labels_test_rec_gt_99.sum()
print("Reduction in number of alarms: %i --> %i (%.1f%%)"%(nBefore_new,nAfter_new,100*(nBefore_new-nAfter_new)/nBefore_new))


'''
fig, ax = plt.subplots()
ax.plot(rec2, prec2, '.')
ax.set(xlabel='Recall', ylabel='Precision', title='Precision vs Recall from X_test (Zoom on Recall > 99%)')
fig.savefig("PrecisionRecall_Zoom99.png")
plt.show()'''


#tt=0.5
#classes = proba[:,1]
#classes[classes>=tt] = 1
#classes[classes<tt] = 0





#Cross customer data validation 

if is_sdn:
    #Load sdn model
    model = sdn_model
    #Load Comporium data
    dfAll_Comp = get_df_from_parquet('/Users/mvenkata/Downloads/FaultData/COMPORIUM_FM_3M/parquet_dataset_90d/comporium_parquet_90d/dataset/')
    dfNonTransient_Comp = dfAll_Comp.loc[(dfAll_Comp['transitory'] == 0)]
    dfNonTransient_Comp['labels'] = dfNonTransient_Comp.apply(lambda row: 1 if ( ( row['lastComment'] != None ) | ( row['serviceId'] != None ) ) else 0, axis=1)
    dfTrain_Comp=dfNonTransient_Comp[['perceivedSeverity','count','duration','labels']]
    dfTrain_Comp=dfTrain_Comp.dropna(how='any')


    #X=dfTrain[['perceivedSeverity','count','duration']].to_numpy()
    X_Comp=dfTrain_Comp[['perceivedSeverity','count','duration']].to_numpy()
    true_labels_comp=dfTrain_Comp[['labels']].to_numpy().ravel()

    #clean-up
    nX_Comp=len(X_Comp)
    i=0
    while i<nX_Comp:
        j=0
        nJ_Comp=len(X_Comp[i])
        while j<nJ_Comp:
            if isinstance(X_Comp[i][j],str):
                s=X_Comp[i][j]
                try:
                    X_Comp[i][j]=float(s)
                except ValueError:
                    v=float(s.split(' ')[0])
                    X_Comp[i][j]=v
                    print("%s --> %f"%(s,v))
            j+=1
        i+=1
        pass
    

    print X_Comp.shape
    print true_labels_comp.shape
    
    from sklearn.model_selection import ShuffleSplit
    ss = ShuffleSplit(n_splits=1, test_size=0.2, random_state=0)
    for train_indices_comp, test_indices_comp in ss.split(X_Comp):
        pass
    
    X_train_Comp=[]
    true_labels_train_comp=[]
    for i in train_indices_comp:
        X_train_Comp.append(X_Comp[i])
        true_labels_train_comp.append(true_labels_comp[i])
        
    X_test_Comp=[]
    true_labels_test_comp=[]
    for i in test_indices_comp:
        X_test_Comp.append(X_Comp[i])
        true_labels_test_comp.append(true_labels_comp[i])
        
    inferred_labels_test_comp=model.predict(X_test_Comp)

    #Evaluate precision, comparing inferred_labels to true_labels
    from sklearn import metrics
    print(metrics.classification_report(true_labels_test_comp,inferred_labels_test_comp,target_names=["noise alarm","important alarm"]))
    print(metrics.confusion_matrix(true_labels_test_comp,inferred_labels_test_comp))
    nBefore=len(inferred_labels_test_comp)
    nAfter=inferred_labels_test_comp.sum()
    print("Reduction in number of alarms: %i --> %i (%.1f%%)"%(nBefore,nAfter,100*(nBefore-nAfter)/nBefore))
    
    proba_comp = model.predict_proba(X_test_Comp)
    prec_comp, rec_comp, thresh_comp = metrics.precision_recall_curve(true_labels_test_comp, proba_comp[:,1], pos_label=None, sample_weight=None)
    
    
    fig, ax = plt.subplots()
    ax.plot(rec, prec, '.')
    ax.set(xlabel='Recall', ylabel='Precision', title='Precision vs Recall from X_test')
    fig.savefig("PrecisionRecall_all.png")
    plt.show()
    
    i=1
    ir=[]
    
    #Index = 57 SDN model Recall > 99% for Cross validation with Comporium data
    #Index = 130 SDN model Recall > 95% for Cross validation with Comporium data
    #Total thresh length 1664
    while i <= 130:
        ir.append(i)
        i = i + 1
        
    #ir=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]thresh
    redux=[]
    rec2=[]
    prec2=[]
    for i in ir:
        tt=thresh[i]
        #tt=0.04459785109680062  #COMPORIUM Recall > 99%
        #tt = 0.10824810556421087 #CMPORIUM Recall > 95%
        #print tt
        classes = model.predict_proba(X_test_Comp)[:,1]
        classes[classes>=tt] = 1
        classes[classes<tt] = 0
        #print classes
        rec2.append(rec[i])
        prec2.append(prec[i])
        redux.append(1 - (classes.sum()/len(classes)))
    
    fig, ax = plt.subplots()
    ax.plot(rec2, redux, '.')
    #ax.set(xlabel='Recall', ylabel='Fraction of alarms kept', title='Alarm Reduction vs Recall from X_test (Zoom on Recall > 99%)')
    ax.set(xlabel='Recall', ylabel='Alarms Reduction', title='Alarm Reduction vs Recall from X_test')
    fig.savefig("/Users/mvenkata/Downloads/FaultData/PR/SDN_AlarmsReductionVsRecall_Cross_validation_gt95.png")
    plt.show()

    #Reports for threshold probability Recall = 99%, threshold = 0.021043011404930652(SDN)
    threshold = 0.03120391585959683
    inferred_labels_test_rec_gt_99_comp = model.predict_proba(X_test_Comp)[:,1]
    inferred_labels_test_rec_gt_99_comp[inferred_labels_test_rec_gt_99_comp>=threshold] = 1
    inferred_labels_test_rec_gt_99_comp[inferred_labels_test_rec_gt_99_comp<threshold] = 0
    from sklearn import metrics
    print(metrics.classification_report(true_labels_test_comp,inferred_labels_test_rec_gt_99_comp,target_names=["noise alarm","important alarm"]))
    print(metrics.confusion_matrix(true_labels_test_comp,inferred_labels_test_rec_gt_99_comp))
    nBefore_new=len(inferred_labels_test_rec_gt_99_comp)
    nAfter_new=inferred_labels_test_rec_gt_99_comp.sum()
    print("Reduction in number of alarms: %i --> %i (%.1f%%)"%(nBefore_new,nAfter_new,100*(nBefore_new-nAfter_new)/nBefore_new))
