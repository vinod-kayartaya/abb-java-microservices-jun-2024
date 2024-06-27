package com.abb.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping(produces = "text/plain")
    public String sayHello(){
        return "Hello from Spring boot";
    }

    @GetMapping(produces = "text/html")
    public String sayHelloAsHtml(){
        StringBuffer html = new StringBuffer();
        html.append("<h1>Hello from Spring boot</h1>");
        html.append("<hr />");
        html.append(("<p>message by Vinod</p>"));
        return html.toString();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> sayHelloAsJson(){
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("message", "Hello, from Spring boot");
        resp.put("from", "Vinod Kumar Kayartaya");
        resp.put("timestamp", new Date());

        return resp;
    }

}
