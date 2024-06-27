package com.abb.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 
 * @author Vinod
 */
@Data
@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstname;
    private String lastname;
    private Gender gender;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private String city;
}
