package com.iwaconsolti.school.miguel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Component
public class Students {
    private String id,age;
    private String firstName,lastName;
}
