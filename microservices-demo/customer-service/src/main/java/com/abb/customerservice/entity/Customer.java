package com.abb.customerservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="customers")
public class Customer implements Serializable {
    @Id
    @Column(name="customer_id")
    private String customerId;
    @Column(name="company_name")
    private String companyName;
    @Column(name="contact_name")
    private String contactName;
    @Column(name="contact_title")
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    @Column(name="postal_Code")
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
}
