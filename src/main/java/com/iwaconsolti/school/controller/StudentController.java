package com.iwaconsolti.school.controller;

import com.iwaconsolti.school.controller.request.StudentRequest;
import com.iwaconsolti.school.model.Student;
import com.iwaconsolti.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/{schoolName}/student")
public class StudentController {

    private final StudentService gerardoStudentService;
    private final StudentService zetStudentService;

    @Autowired
    public StudentController(StudentService gerardoStudentService, StudentService zetStudentService) {
        this.gerardoStudentService = gerardoStudentService;
        this.zetStudentService = zetStudentService;
    }

    private StudentService getServiceBySchoolName(String schoolName) {
        if ("GerardoInstitute".equalsIgnoreCase(schoolName)) {
            return gerardoStudentService;
        } else if ("ZetCollege".equalsIgnoreCase(schoolName)) {
            return zetStudentService;
        } else {
            throw new IllegalArgumentException("Invalid school name");
        }
    }


}
