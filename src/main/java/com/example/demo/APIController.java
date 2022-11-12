package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by @author Harish Patil on 08-11-2022
 *
 */
@RestController
public class APIController {

    @GetMapping("/home")
    public ResponseEntity<Object> home(){
        return new ResponseEntity<>("Welcome Home Page!", HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<Object> health(){
        return new ResponseEntity<>("Welcome health Page!", HttpStatus.OK);
    }
}
