package com.iwaconsolti.school.miguel.model.dto;

import lombok.Data;

@Data
public class GradesDTO {
    private int id;
    private long score;
    private int studentId;
    private int courseId;
}
