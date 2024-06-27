package com.abb.customerservice.repository;

import com.abb.customerservice.entity.Customer;
import com.abb.customerservice.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    public Customer findByEmail(String email);
    public Customer findByPhone(String phone);
    public List<Customer> findAllByCity(String city);

    // @Query("select c.firstname from Customer c where c.gender = 0")
    @Query(nativeQuery = true, value = "select concat(firstname, ' ', lastname) from customers")
    public List<String> findAllMaleCustomerNames();
}
