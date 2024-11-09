package com.iwaconsolti.school.demo.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
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
