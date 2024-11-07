package com.mycompany.servicetwo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceTwoController {


    @GetMapping("greetings")
    public ResponseEntity<String> greetings() {
        return ResponseEntity.ok("Hello from service two !!!");
    }

    @GetMapping("/dummy-data")
    public ResponseEntity<String> dummyData() {
        return ResponseEntity.ok("dummy data from service two !!!");
    }

}
