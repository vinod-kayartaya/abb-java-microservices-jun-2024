package com.abb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ehs")
@Slf4j
public class EnglishHelloService implements HelloService{

    public EnglishHelloService() {
        log.debug("EnglishHelloService constructor called");
    }

    @Autowired
    FormatterService formatter;

    @Override
    public void sayHello() {
        System.out.println(formatter.formatAsBox("Hello World!"));
    }
}
