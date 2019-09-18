#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Mon Apr 24 23:15:32 2017

@author: haiyingliu
"""
import numpy as np
from sklearn import datasets
from sklearn.naive_bayes import GaussianNB
import MySQLdb
from datetime import datetime, timedelta
from sklearn import svm
from sklearn.neural_network import MLPRegressor
import BaysianCurveFitting as bc

db = MySQLdb.connect("localhost","root","1119","StockTestDatabase" )
cursor = db.cursor()

sql = "SELECT * FROM Stock;"
result = cursor.execute(sql)
result = cursor.fetchall()

num_rows = 40
num_cols_x = 10
num_bayes_history_day = 20
for item in result:
    sql = '''SELECT H.Close FROM Historical H, Stock S 
            WHERE H.Stock_Id = %s ORDER BY Time DESC;'''%(item[0])
    price_result = cursor.execute(sql)
    price_result = cursor.fetchall()
    X = []
    y = []
    x_final_row = []
    X_bayes = []
    
    for row in range(0, num_rows):
        if (row + num_cols_x >= len(price_result)):
            break
        y.append(int(price_result[row][0] * 1000))
        x_row = []
        for col_x in range(0, num_cols_x):
            x_row.append(price_result[row + 1 + col_x][0])
        X.append(x_row)
    
    for row in range(0, num_bayes_history_day):
        X_bayes.append(price_result[row][0])
        
    x_row = []
    for col_x in range(0, num_cols_x):
        x_row.append(price_result[col_x][0])
    x_final_row.append(x_row)
            
    #######################
    ##### Naive Bayes #####
    #######################
    N = 20
    add = item[1] + "_baysian.txt"
    bc.baysian_curve_fitting(N, X_bayes, y[0] * 0.001, add)
    '''
    gnb = GaussianNB()
    X_np = np.asarray(X)
    y_np = np.asarray(y)
    x_final_np = np.asarray(x_final_row)
    model = gnb.fit(X_np, y_np)
    y_pred = model.predict(x_final_np)
    #print y_pred
    fo = open(item[1] + "_baysian.txt", "w")
    fo.write(str(round(y_pred[0] * 0.001,2)) + ","
             + str(round((y_pred[0] - y[0]) / y[0], 4)))
    fo.close()
    '''
    #######################
    #####     SVM     #####
    #######################
    svm_model = svm.SVR()
    svm_model.fit(X, y)
    y_pred_svm = svm_model.predict(x_final_row)
    fo_svm = open(item[1] + "_svm.txt", "w")
    fo_svm.write(str(round(y_pred_svm[0] * 0.001,2)) + "," + 
                 str(round((y_pred_svm[0] - y[0]) / y[0], 4)))
    fo_svm.close()
    
    #######################
    #####     ANN     #####
    #######################
    ann = MLPRegressor(solver='lbfgs', hidden_layer_sizes=5,
                           max_iter=50, shuffle=True, random_state=1)
    ann.fit(X, y)
    y_pred_ann = ann.predict(x_final_row)
    fo_ann = open(item[1] + "_ann.txt", "w")
    fo_ann.write(str(round(y_pred_ann[0] * 0.001,2))+ "," + 
                 str(round((y_pred_ann[0] - y[0]) / y[0], 4)))
    fo_ann.close()
    
