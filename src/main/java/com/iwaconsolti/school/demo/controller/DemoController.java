package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.RequestDemo;
import com.iwaconsolti.school.demo.model.Student;
import com.iwaconsolti.school.demo.service.DemoService;
import com.iwaconsolti.school.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
@Slf4j
@RequiredArgsConstructor
public class DemoController {
    @Autowired  private StudentService studentService;


    @PostMapping("/addStudent")
    public String registerStudent(@RequestParam int id, @RequestParam String firstName , @RequestParam String lastName, @RequestParam int age) {
        return studentService.saveStudent(id, firstName, lastName, age);
    }



    /*

    private final DemoService service;

    @GetMapping("/")
    public ResponseEntity<List<String>> getDemo(){
        log.info("enter");
        return ResponseEntity.ok(service.getNames());
    }

    @PostMapping("/")
        public ResponseEntity<Void> postDemo(@RequestBody final RequestDemo demoRequest){
            log.info("Request {}", demoRequest);
            service.insertName(demoRequest.getName());
        return ResponseEntity.noContent().build();
        }*/
}
