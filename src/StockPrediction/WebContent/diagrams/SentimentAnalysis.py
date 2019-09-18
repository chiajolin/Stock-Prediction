# -*- coding: utf-8 -*-
"""
Created on Sun April 10 20:39:41 2017

@author: Haiying Liu
"""
#from flask import Flask
#from flask import render_template
import MySQLdb

import matplotlib
import mpld3
from mpld3 import plugins, utils
import csv
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import json
from twitter import Twitter
from twitter import OAuth
from twitter import TwitterHTTPError
from twitter import TwitterStream
from pandas.io.json import json_normalize
from textblob import TextBlob
import time




def sentimentAnalysis():

    ACCESS_TOKEN = '799343128826220545-tCJmBpnflo9XVctgJV9RPqsvLqamAlp'
    ACCESS_SECRET = '8yIVLod4G8GBLf8BytJn1jEmsOOHDDmh2vXpbKGo2BIgt'
    consumer_key = 'IdyJD6ADyI9UNwsOX7rWkXhBm'
    consumer_secret = 'oBtmGge4sJUwAXqK9AEWEedFA2VECEhAxGTsLlhc1Ngq7aEyIR'
    oauth = OAuth(ACCESS_TOKEN, ACCESS_SECRET, consumer_key, consumer_secret)
    api = Twitter(auth=oauth)
    quote = []
    
    db = MySQLdb.connect("localhost","root","1119","StockTestDatabase" )
    cursor = db.cursor()
    
    sql = "SELECT Name FROM Stock;"
    result = cursor.execute(sql)
    result=cursor.fetchall()
    print result
    t = list(result)
    for item in t:
        
        quote.append('#'+str(item[0]))
    #print quote

#quote = ['#YHOO','#GOOG','#FB','#AMZN','#MSFT','#WMT','#SAP','#INTC','#CCF','#VZ']
    for qq in quote:
        search_results = api.search.tweets(q=qq,count = 100)
        df = json_normalize(search_results, 'statuses')
        
        s = np.empty(1)
        p = np.empty(1)
        
        for x in df['text']:
            w = TextBlob(x)
            w.sentiment
            s=np.append(s,w.subjectivity)
            p=np.append(p,w.polarity)
            
        df['polarity']=s[1:101]
        
        #hidf = df[df['polarity']>=.7]
        #hisn=json_normalize(hidf['entities'],'user_mentions')
        
        df2 = pd.DataFrame({'subjectivity':s,'polarity':p})
        sizes = [0,0,0,0,0]
        for i in df2['polarity']:
            if i >= 0.6:
                sizes[0] += 1
            elif i < 0.6 and i >= 0.2:
                sizes[1] += 1
            elif i >= -0.2 and i < 0.2:
                sizes[2] += 1 
            elif i >= -0.6 and i < -0.2:
                sizes[3] += 1
            else:
                sizes[4] += 1
                     
            
        #df2.plot()
        fig,ax = plt.subplots()
        
        labels = 'very positive', 'positive', 'neutral', 'negative', 'very negative'
        
        colors = ['gold', 'yellowgreen', 'lightcoral', 'lightskyblue','red']
        explode = (0, 0, 0, 0, 0)  # explode 1st slice
         
        # Plot
        xx = ax.pie(sizes, explode=explode, labels=labels, colors=colors,
                autopct='%1.1f%%', shadow=True, startangle=140)
         
        plt.axis('equal')
        plt.axis('off')
        ax.axes.get_yaxis().set_visible(False)
        #plt.show()
        tooltip = plugins.PointHTMLTooltip(xx)
        plugins.connect(fig, tooltip)
        
        '''
        ax.set_title('HTML tooltips', size=20)
        xx = ax.plot(df2['subjectivity'])
        #yy = ax.plot(df2['polarity'])
        tooltip = plugins.PointHTMLTooltip(xx)
        plugins.connect(fig, tooltip)
        '''
        #mpld3.show()
        file_add = qq.split('#')[1] + '.csv'
        #add = qq.split('#')[1] + '_fig.html'
        #print add
        #mpld3.save_html(fig, add)
        csvfile = file(file_add, 'wb')
        writer = csv.writer(csvfile)
        writer.writerow(sizes)
        csvfile.close()
    time.sleep(3600)
        
        #fig = df2.plot()
        
        #lidf = df[df['polarity']<=-0.7]
        #hili_df = pd.concat([hidf,lidf],axis=0)
        #hilisn=json_normalize(hili_df['entities'],'user_mentions')
        #print(hilisn)
    
while True:
    sentimentAnalysis()
