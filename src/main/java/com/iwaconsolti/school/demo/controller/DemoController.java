package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.RequestDemo;
import com.iwaconsolti.school.demo.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
@Slf4j
@RequiredArgsConstructor
public class DemoController {

    private final DemoService service;

    @GetMapping("/")
    public ResponseEntity<List<String>> getDemo(){
        log.info("enter");
        return ResponseEntity.ok(service.getNames());
    }

@PostMapping("/")
    public ResponseEntity<Void> postDemo(@RequestBody final RequestDemo demoRequest){
        log.info("Request {}", demoRequest);
        log.info("Request Demo {}", demoRequest);
        service.insertName(demoRequest.getName());
    return ResponseEntity.noContent().build();
    }
}
