package com.iwaconsolti.school.miguel.persistence.model.dto;

import lombok.Data;

@Data
public class GradesDTO {
    private int id;
    private long score;
    private int studentId;
    private int courseId;
}
