package com.iwaconsolti.school.miguel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
@Component
public class Students {
    private int id;
    private String firstName,lastName;
    private int age;
}
