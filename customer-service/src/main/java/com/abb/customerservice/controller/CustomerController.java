package com.abb.customerservice.controller;

import com.abb.customerservice.dto.CustomerDTO;
import com.abb.customerservice.exception.ErrorData;
import com.abb.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<CustomerDTO> handleGetAll(){
        return service.getAllCustomers();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity handleGetOne(@PathVariable UUID id){
        CustomerDTO customer = service.getCustomerById(id);
        if(customer==null){
            ErrorData err = new ErrorData("no customer found for id " + id);
            return ResponseEntity.status(404).body(err);
        }

        return ResponseEntity.ok(customer);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity handlePostOne(@Valid @RequestBody CustomerDTO customerDTO){
        customerDTO =  service.addNewCustomer(customerDTO);
        return ResponseEntity.status(201).body(customerDTO);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity handlePut(@PathVariable UUID id, @Valid @RequestBody CustomerDTO customerDTO){
        CustomerDTO existingCustomer = service.getCustomerById(id);
        if(existingCustomer==null){
            return new ResponseEntity(new ErrorData("no customer for this id"), HttpStatus.NOT_FOUND);
        }

        customerDTO.setId(id);
        service.updateCustomer(customerDTO);
        return new ResponseEntity(customerDTO, HttpStatus.OK);    }


    @PatchMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity handlePatch(@PathVariable UUID id, @RequestBody CustomerDTO customerDTO){
        CustomerDTO existingCustomer = service.getCustomerById(id);
        if(existingCustomer==null){
            return new ResponseEntity(new ErrorData("no customer for this id"), HttpStatus.NOT_FOUND);
        }

        // copy the data from customerDTO to existingCustomerDTO
        if(customerDTO.getFirstname()!=null){
            existingCustomer.setFirstname(customerDTO.getFirstname());
        }
        if(customerDTO.getLastname()!=null){
            existingCustomer.setLastname(customerDTO.getLastname());
        }
        if(customerDTO.getGender()!=null){
            existingCustomer.setGender(customerDTO.getGender());
        }
        if(customerDTO.getEmail()!=null){
            existingCustomer.setEmail(customerDTO.getEmail());
        }
        if(customerDTO.getPhone()!=null){
            existingCustomer.setPhone(customerDTO.getPhone());
        }
        if(customerDTO.getCity()!=null){
            existingCustomer.setCity(customerDTO.getCity());
        }

        service.updateCustomer(existingCustomer);
        return new ResponseEntity(existingCustomer, HttpStatus.OK);
    }

    @PostMapping(path="/bulk", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity handlePostMany(@RequestBody List<CustomerDTO> customerDTOList){
        // customerDTOList.stream().forEach(service::addNewCustomer);
        /*List<UUID> generatedIds = new ArrayList<>();
        customerDTOList.stream()
                .forEach(c -> {
                    c = service.addNewCustomer(c);
                    generatedIds.add(c.getId());
                });*/
        List<UUID> generatedIds = service.addMultipleCustomers(customerDTOList).stream().map(c->c.getId()).toList();
        return ResponseEntity.ok(generatedIds);

    }

}
