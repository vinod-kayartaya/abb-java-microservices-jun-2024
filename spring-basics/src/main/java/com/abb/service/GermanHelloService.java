package com.abb.service;

public class GermanHelloService implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("Hello Welt!");
    }
}
