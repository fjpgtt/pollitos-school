package com.iwaconsolti.school.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Grades")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private long score;
    private int studentId;
    private int courseId;
    private Date creationDate;
}
