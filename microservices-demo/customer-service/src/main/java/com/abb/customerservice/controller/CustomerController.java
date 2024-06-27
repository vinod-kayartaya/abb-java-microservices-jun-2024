package com.abb.customerservice.controller;

import com.abb.customerservice.entity.Customer;
import com.abb.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URI;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/download/{filename}")
    public ResponseEntity handleDownloadFile(@PathVariable String filename) {
        try (InputStream stream = service.downloadFile(filename)) {
            return ResponseEntity.ok(stream.readAllBytes());
        } catch (Exception ex) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("timestamp", new Date());
            response.put("success", false);
            response.put("error", ex.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


    @PostMapping("/upload")
    public Map<String, Object> handleUploadFile(@RequestParam MultipartFile file) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", new Date());

        try {
            service.uploadFile(file.getOriginalFilename(),
                    file.getInputStream(),
                    file.getContentType());

            response.put("success", true);
            response.put("message", "file upload was successful!");
        } catch (Exception ex) {
            response.put("success", false);
            response.put("error", ex.getMessage());
        }

        return response;
    }


    @GetMapping
    public List<Customer> handleGetAll() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity handleGetOne(@PathVariable String id) {
        var data = service.getCustomer(id);
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity handlePost(@RequestBody Customer customer) throws Exception {
        service.saveCustomer(customer);
        return ResponseEntity
                .created(new URI("/api/customers/" + customer.getCustomerId()))
                .body(customer);
    }
}
