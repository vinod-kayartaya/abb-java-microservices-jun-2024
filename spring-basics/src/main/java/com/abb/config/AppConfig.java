package com.abb.config;

import com.abb.service.GermanHelloService;
import com.abb.service.JapaneseHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ComponentScan(basePackages = {"com.abb.service"})
// A component is a class annotated with one of the following:
// @Component, @Service, @Repository, @Controller, @RestController, @Configuration
public class AppConfig {
    public AppConfig() {
        log.debug("AppConfig constructor called");
    }

    @Bean
    public GermanHelloService ghs(){
        log.debug("AppConfig.ghs() called to return an object GermanHelloService");
        return new GermanHelloService();
    }

}
