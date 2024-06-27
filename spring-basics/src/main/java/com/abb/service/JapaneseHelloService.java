package com.abb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("jhs")
@Slf4j
public class JapaneseHelloService implements HelloService{

    public JapaneseHelloService() {
        log.debug("JapaneseHelloService constructor called");
    }

    // dependency
    @Autowired
    private FormatterService formatter;

    @Override
    public void sayHello() {
        System.out.println(formatter.formatAsBox("こんにちは世界！"));
    }
}
