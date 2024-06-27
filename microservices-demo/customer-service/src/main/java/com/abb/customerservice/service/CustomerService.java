package com.abb.customerservice.service;

import com.abb.customerservice.entity.Customer;
import com.abb.customerservice.repository.CustomerRepository;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private CustomerRepository repo;

    @Value("${minio.bucket-name}")
    private String bucketName;

    public void uploadFile(String objectName, InputStream inputStream, String contentType) throws Exception {
        PutObjectArgs args = PutObjectArgs
                .builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(inputStream, inputStream.available(), -1)
                .contentType(contentType)
                .build();

        minioClient.putObject(args);
    }

    public InputStream downloadFile(String objectName) throws Exception {
        GetObjectArgs args = GetObjectArgs
                .builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        return minioClient.getObject(args);
    }


    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @Cacheable(value = "customers", key = "#id")
    public Customer getCustomer(String id) {
        return repo.findById(id).orElse(null);
    }

    public void saveCustomer(Customer customer) {
        repo.save(customer);
    }
}
