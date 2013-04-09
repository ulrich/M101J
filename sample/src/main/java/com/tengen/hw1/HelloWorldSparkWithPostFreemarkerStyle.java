package com.tengen.hw1;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class HelloWorldSparkWithPostFreemarkerStyle {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        get(new Route("/hello") {
            @Override
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();

                try {
                    Template template = configuration.getTemplate("hello.ftl");

                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("fruits",
                            Arrays.asList("Apple", "Banana", "Orange", "Peach"));

                    template.process(map, writer);

                } catch (Exception e) {
                    halt(500);
                }
                return writer;
            }
        });
    }
}
