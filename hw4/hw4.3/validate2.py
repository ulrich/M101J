import pymongo
import random
import re
import string
import sys
import getopt
import pprint

# Copyright 2012
# 10gen, Inc.
# Author: Andrew Erlichson   aje@10gen.com
#
# If you are a student and reading this code, turn back now, before
# the MongoDB gods smite you.

connection = None
db = None
webhost = "localhost:8082"
mongostr = "mongodb://localhost:27017"
db_name = "blog"

# this script will check that homework 4.3

# command line arg parsing to make folks happy who want to run at mongolabs or mongohq
# this functions uses global vars to communicate. forgive me.
def arg_parsing(argv):

    global webhost
    global mongostr
    global db_name

    try:
        opts, args = getopt.getopt(argv, "-p:-m:-d:")
    except getopt.GetoptError:
        print "usage validate.py -p webhost -m mongoConnectString -d databaseName"
        print "\tmongoConnectionString default to {0}".format(mongostr)
        print "\tdatabaseName defaults to {0}".format(db_name)
        sys.exit(2)
    for opt, arg in opts:
        if (opt == '-h'):
            print "usage validate.py -m mongoConnectString -d databaseName"
            sys.exit(2)
        elif opt in ("-m"):
            mongostr = arg
            print "Overriding MongoDB connection string to be ", mongostr
        elif opt in ("-d"):
            db_name = arg
            print "Overriding MongoDB database to be ", db_name

# check to see if they loaded the data set
def check_for_data_integrity():

    posts = db.posts
    try:
        count = posts.count()
    except:
        print "can't query MongoDB..is it running