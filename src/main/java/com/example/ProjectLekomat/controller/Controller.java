package com.example.ProjectLekomat.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/get")
    String get(){
        String get = "ok";
        return get;
    }


}
