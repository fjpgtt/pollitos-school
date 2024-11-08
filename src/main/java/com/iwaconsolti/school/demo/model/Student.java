package com.iwaconsolti.school.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;

}
