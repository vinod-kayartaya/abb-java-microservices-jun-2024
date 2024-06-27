package com.abb.customerservice.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "First name is required, but missing")
    @Size(max = 20, min = 2)
    private String firstname;
    @Size(max = 20, min = 2)
    private String lastname;
    private Gender gender;

    @Email(message = "Email must be a valid one")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Column(unique = true)
    private String phone;
    private String city;
}

