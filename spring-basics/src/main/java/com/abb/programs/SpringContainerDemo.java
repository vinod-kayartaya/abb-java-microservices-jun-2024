package com.abb.programs;

import com.abb.config.AppConfig;
import com.abb.service.EnglishHelloService;
import com.abb.service.HelloService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContainerDemo {

    public static void main(String[] args) {
        HelloService service; // my dependency

        // service = new EnglishHelloService(); // tight coupling

        // we want to create a spring container, that maintains the required beans (object of classes)
        // A spring container is represented by an object of the interface ApplicationContext

        // some of the classes of type ApplicationContext are:
        // 1. ClassPathXmlApplicationContext (depends on an XML file in the classpath)
        // 2. FileSystemXmlApplicationContext (An xml file in the os file system)
        // 3. AnnotationConfigApplicationContext (depends on configuration expressed using annotations)
        try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)){
            service = ctx.getBean("ehs", HelloService.class); // loose coupling; spring decides what object will you receive
            service.sayHello();
        } // ctx.close() called here
    }
}
