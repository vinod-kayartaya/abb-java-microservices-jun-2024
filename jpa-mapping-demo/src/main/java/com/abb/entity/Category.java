package com.abb.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"products", "picture"})
@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private byte[] picture;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Product> products = new ArrayList<>();

}
