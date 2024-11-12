package com.iwaconsolti.school.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@ToString
@Builder
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private Date creationDate;
    private List<Grade> grades;

}
