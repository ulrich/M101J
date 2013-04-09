package com.tengen.hw1;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldFreemarkerStyle {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        Template template = configuration.getTemplate("hello.ftl");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "World!");

        StringWriter writer = new StringWriter();

        template.process(map, writer);

        System.out.println(writer);
    }
}
