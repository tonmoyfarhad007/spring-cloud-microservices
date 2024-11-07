package com.mycompany.serviceone.controller;

import com.mycompany.serviceone.service.otherservicecall.ServiceTwoCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServiceOneController {
    private final ServiceTwoCallService serviceTwoCallService;


    @GetMapping("/greetings")
    public ResponseEntity<String> greetings() {
        return ResponseEntity.ok( "Hello from service one !!!");
    }

    @GetMapping("/admin/greetings")
    public ResponseEntity<String> adminGreetings() {
        return ResponseEntity.ok("Hello from service one's ADMIN !!!");
    }

    @GetMapping("/other-service-data")
    public ResponseEntity<String> otherServiceData() {
        String dummyData = serviceTwoCallService.getServiceTwoDummyData();
        return ResponseEntity.ok(dummyData);
    }
}
