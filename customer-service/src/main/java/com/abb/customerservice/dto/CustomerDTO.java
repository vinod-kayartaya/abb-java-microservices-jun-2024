package com.abb.customerservice.dto;

import com.abb.customerservice.entity.Customer;
import com.abb.customerservice.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class CustomerDTO {
    private UUID id;

    @NotBlank(message = "First name is required, but missing")
    @Size(max = 20, min = 2)
    private String firstname;

    @Size(max = 20, min = 2)
    private String lastname;

    private Gender gender;

    @NotBlank(message = "Email is required, but missing")
    @Email(message = "Email must be a valid one")
    private String email;

    @NotBlank(message = "Phone is required, but missing")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

    private String city;

    public static Customer toCustomer(CustomerDTO dto){
        if(dto==null) return null;

        Customer customer = new Customer();
        customer.setId(dto.id);
        customer.setFirstname(dto.firstname);
        customer.setLastname(dto.lastname);
        customer.setEmail(dto.email);
        customer.setPhone(dto.phone);
        customer.setCity(dto.city);
        customer.setGender(dto.gender);
        return customer;
    }

    public static CustomerDTO toCustomerDTO(Customer customer){
        if(customer==null) return null;

        CustomerDTO dto = new CustomerDTO();
        dto.id = customer.getId();
        dto.firstname = customer.getFirstname();
        dto.lastname = customer.getLastname();
        dto.gender = customer.getGender();
        dto.email = customer.getEmail();
        dto.phone = customer.getPhone();
        dto.city = customer.getCity();
        return dto;
    }
}
