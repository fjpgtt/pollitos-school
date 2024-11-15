package com.iwaconsolti.school.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@Builder
public class Course {

    private int id;
    private String name;
    private String professorName;
    private Date creationDate;

}
