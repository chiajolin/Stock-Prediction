#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sat Apr 22 10:49:12 2017

@author: haiyingliu
"""
import math
import MySQLdb
import pandas as pd
import pandas_datareader as pdr
from stockstats import StockDataFrame as Sdf
import datetime
import csv
import time
now = datetime.datetime.now()
oneyear = datetime.datetime.now() - datetime.timedelta(days=365)

def write_file(item, indicator_type):
    fileadd = item+'_' + indicator_type + '.json'
    json_file = open(fileadd, 'w')
    json_file.write('[')
    
    x= 0
    first_flag = True;
    for i in stock_df.index:
        time_stamp = time.mktime(datetime.datetime.strptime(str(i), "%Y-%m-%d %H:%M:%S").timetuple())
        if(math.isnan(stock_name[indicator_type][x]) == False):
            if (first_flag == True) : 
                first_flag = False
            else :
                json_file.write(',\n')
            json_file.write('[' + str(int(time_stamp * 1000)) + ',' + str(stock_name[indicator_type][x]) + ']')
        x += 1
        
    json_file.write(']')
    json_file.close()
    


db = MySQLdb.connect("localhost","root","1119","StockTestDatabase" )
cursor = db.cursor()
    
sql = "SELECT Name FROM Stock;"
result = cursor.execute(sql)
result=cursor.fetchall()
for item in result:
    stock_name = pdr.get_data_yahoo(symbols=str(item[0]), start=oneyear, end=now)
    stock_df = Sdf.retype(stock_name)
    #rsi_14
    stock_name['rsi']=stock_df['rsi_14']
    write_file(str(item[0]), 'rsi')
    #macd
    stock_name['macd'] = stock_df.get('macd')
    write_file(str(item[0]), 'macd')
    #dma
    stock_name['dma'] = stock_df['dma']
    write_file(str(item[0]), 'dma')