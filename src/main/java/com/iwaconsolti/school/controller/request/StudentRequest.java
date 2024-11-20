package com.iwaconsolti.school.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRequest {
    private final int id;
    private String firstName;
    private String lastName;
    private int age;
}
