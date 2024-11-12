package com.iwaconsolti.school.demo.controller;

import com.iwaconsolti.school.demo.model.RequestDemo;
import com.iwaconsolti.school.demo.model.Student;
import com.iwaconsolti.school.demo.service.DemoService;
import com.iwaconsolti.school.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
@Slf4j
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;
    private final StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<String>> getDemo(){
        log.info("enter");
        return ResponseEntity.ok(demoService.getNames());
    }

    @PostMapping("/user")
    public ResponseEntity<Void> postDemo(@RequestBody final RequestDemo demoRequest){
        log.info("Request {}", demoRequest);
        demoService.insertName(demoRequest.getName());
    return ResponseEntity.noContent().build();
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(){
        log.info("Getting all the students");
        return ResponseEntity.ok(studentService.getStudents());
    }
}
