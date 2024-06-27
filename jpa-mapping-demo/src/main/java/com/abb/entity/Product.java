package com.abb.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double unitPrice;
    @Column(name="unit_description")
    private String unitDescription;
    @Column(name="units_in_stock")
    private Integer unitsInStock;
    private Boolean discontinued = false;

    @ManyToOne
    @JoinColumn(name="cat_id")
    private Category category;

    public void setCategory(Category category) {
        this.category = category;
        category.getProducts().add(this);
    }
}
