package com.tengen;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;

import static spark.Spark.get;

public class HelloWorldMongoDBSparkFreemarkerStyle {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        get(new Route("/hello") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();

                try {
                    Template template = configuration.getTemplate("hello.ftl");

                    MongoClient mongoClient = new MongoClient();

                    DB course = mongoClient.getDB("course");

                    DBCollection helloCollection = course.getCollection("hello");

                    DBObject dbObject = helloCollection.findOne();

                    template.process(dbObject, writer);

                } catch (Exception e) {
                    halt(500);
                }
                return writer;
            }
        });
    }
}
