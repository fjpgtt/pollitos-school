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
public class Grade {

    private int id;
    private long score;
    private int studentId;
    private int courseId;
    private Date creationDate;

}
