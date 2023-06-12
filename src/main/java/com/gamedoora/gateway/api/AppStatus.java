package com.gamedoora.gateway.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AppStatus {

    @GetMapping("/health")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("Up & Running", HttpStatus.OK);
    }

    @GetMapping("/secret")
    public ResponseEntity<String> secret() {
        return new ResponseEntity<>("Shhh..", HttpStatus.OK);
    }
}
