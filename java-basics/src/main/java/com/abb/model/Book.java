package com.abb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer id;
    private String title;
    private String author;
    private Double price;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
