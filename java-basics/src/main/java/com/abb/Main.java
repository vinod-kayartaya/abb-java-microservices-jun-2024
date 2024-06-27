package com.abb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abb.model.Book;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Book b1;

        b1 = new Book();
        b1.setId(1234);
        b1.setTitle("Let us C");
        b1.setAuthor("Y. Kanitkar");
        b1.setPrice(784.);

        log.error("value of b1 is {}", b1);
    }
}