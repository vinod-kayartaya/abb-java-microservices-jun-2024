package com.abb.customerservice.service;

import com.abb.customerservice.dto.CustomerDTO;
import com.abb.customerservice.entity.Customer;
import com.abb.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public List<CustomerDTO> getAllCustomers(){
        return repo.findAll()
                .stream()
                .map( CustomerDTO::toCustomerDTO )
                .toList();
    }

    public CustomerDTO getCustomerById(UUID id) {
        return CustomerDTO.toCustomerDTO(repo.findById(id).orElse(null));
    }

    public CustomerDTO addNewCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerDTO.toCustomer(customerDTO);
        customer = repo.save(customer); // since id is not present in customer, it will insert the record
        return CustomerDTO.toCustomerDTO(customer);
    }

    public List<CustomerDTO> addMultipleCustomers(List<CustomerDTO> customerDTOList){
        var customers = customerDTOList.stream().map(CustomerDTO::toCustomer).toList();
        return repo.saveAll(customers).stream().map(CustomerDTO::toCustomerDTO).toList();
    }

    public void updateCustomer(CustomerDTO existingCustomer) {
        Customer customer = CustomerDTO.toCustomer(existingCustomer);
        repo.save(customer); // since id is present in customer, it will do a sql update
    }
}
