package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.model.Course;
import com.iwaconsolti.school.model.Grade;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    // Obtener todas las calificaciones
    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades() {
        List<Grade> grades = gradeService.getAllGrades();
        return ResponseEntity.ok(grades);
    }

    // Obtener todas las calificaciones de un estudiante
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getGradesByStudent(@PathVariable Integer studentId) {
        List<Grade> grades = gradeService.getGradesByStudent(studentId);
        return ResponseEntity.ok(grades);
    }

    // Agregar una nueva calificación
    @PostMapping
    public ResponseEntity<Grade> addGrade(@RequestBody Grade grade) {
        Grade addedGrade = gradeService.addGrade(grade);
        return ResponseEntity.status(201).body(addedGrade);
    }

    // Editar una calificación existente
    @PutMapping("/{gradeId}")
    public ResponseEntity<Grade> editGrade(@PathVariable Integer gradeId, @RequestBody Grade grade) {
        grade.setId(gradeId);
        Grade updatedGrade = gradeService.editGrade(grade);
        return ResponseEntity.ok(updatedGrade);
    }

    // Eliminar todas las calificaciones de un estudiante
    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteAllGradesOfStudent(@PathVariable Integer studentId) {
        gradeService.deleteAllGradesOfStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    // Eliminar todas las calificaciones de un curso
    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<Void> deleteAllGradesOfCourse(@PathVariable Integer courseId) {
        gradeService.deleteAllGradesOfCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
