#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sun Feb 19 23:57:47 2017

@author: haiyingliu
"""
import numpy as np

def baysian_curve_fitting (N, t, price_realtime,add):
    M = 5
    alpha = 0.005
    beta = 10
    x = np.arange(1, N+2, 1)
    
        
    Phi__traverse_x = np.zeros(shape=(1,M+1))
    for i in range (0, M+1):
        Phi__traverse_x[0][i] = np.power(x[N],i)
           
    Phi__traverse_x_matrix = np.matrix(Phi__traverse_x)
    #print(Phi__traverse_x_matrix)
    
    Phi_x_n = np.zeros(shape=(M+1,1))
    for i in range(0,N):
        for j in range(0,M+1):
            Phi_x_n[j][0] += np.power(x[i],j)
    Phi_x_n_matrix = np.matrix(Phi_x_n)
    #print(Phi__traverse_x_n_matrix)
    S_pre = (np.dot(Phi_x_n_matrix, Phi__traverse_x_matrix)) * beta
    S_pre_matrix = np.matrix(S_pre)
    S_array = S_pre_matrix.getA()
    for i in range(0,M+1):
        for j in range(0,M+1):
            if i == j:
                S_array[i][j] += alpha
    #I = np.eye(M+1)
    #S_post = I*alpha + S_pre
    S = (np.matrix(S_array)).I
    
    Last_half = np.zeros(shape=(M+1,1))
    for i in range (0,N):
        for j in range(0,M+1):
            Last_half[j][0] += np.power(x[i],j) * t[i]
    Last_half_matrix = np.matrix(Last_half)
    #t_matrix = np.matrix(t)
    #Last_half = np.dot(Phi_x_n_matrix, t_matrix)
    First_half = np.dot(Phi__traverse_x_matrix, S)
    m_x = np.matrix(np.dot(First_half, Last_half_matrix)) * beta
    m_x_array = m_x.getA()
    s_square = 1/beta + np.dot(np.dot(Phi__traverse_x_matrix, S),Phi__traverse_x_matrix.T)
    
    mean_error = abs(m_x_array[0][0] - price_realtime)
    average_error = mean_error/price_realtime
    fo = open(add, "w")
    fo.write(str(round(m_x_array[0][0],2)) + ","+ str(round((m_x_array[0][0] - price_realtime) / price_realtime, 4)))
    fo.close()
    #print ("predict price: " + str(m_x_array[0][0]))
    #print ("mean error: " + str(mean_error))
    #print ("average relative error: " + str(average_error))
    
    #print (S)
    
    
        
    