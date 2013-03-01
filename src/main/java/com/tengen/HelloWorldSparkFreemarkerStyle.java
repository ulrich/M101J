package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class HelloWorldSparkFreemarkerStyle {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        get(new Route("/hellofreemarker") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();

                try {
                    Template template = configuration.getTemplate("hello.ftl");

                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", "World!");

                    template.process(map, writer);

                } catch (Exception e) {
                    halt(500);
                }
                return writer;
            }
        });
    }
}
