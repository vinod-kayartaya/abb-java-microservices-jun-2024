package com.abb.authservice.controller;

import com.abb.authservice.model.User;
import com.abb.authservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/api/auth/login")
    public ResponseEntity handleLogin(@RequestBody User user){
        log.debug("got the user as {}", user);
        try{
            return ResponseEntity.ok(service.authenticate(user.getUsername(), user.getPassword()));
        }
        catch(Exception e){
            log.warn("Error while authenticating user", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
