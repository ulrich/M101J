package com.tengen.hw1;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class HelloWorldMongoDBStyle {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();

        DB course = mongoClient.getDB("course");

        DBCollection helloCollection = course.getCollection("hello");

        System.out.println(helloCollection.findOne());

    }
}
