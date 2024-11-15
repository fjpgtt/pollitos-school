package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Grade;
import com.iwaconsolti.school.demo.model.School;
import com.iwaconsolti.school.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

@Service
public class StudentService {
    private final School gerardoInstitute;
    private final School zetCollege;

    @Autowired
    public StudentService(@Qualifier("gerardoInstitute") School gerardoInstitute, @Qualifier("zetCollege") School zetCollege, Grade grade) {
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public String deleteGradesStudent(School school, int id) {
        for (Student existingStudent : school.getStudentsList()) {
            if (existingStudent.getId() == id) {
                boolean removed = school.getGradeList().removeIf(gradeElement -> gradeElement.getStudent() == id); //Delete all grades of the student with the given ID
                if (removed) {
                    return "All grades deleted for student; ID " + id + " | " + existingStudent.toString();
                } else {
                    return "No grades found for student ID " + id;
                }
            }
        }
        return "Student does not exist: " + id;
    }

    public String createStudent(School school, Student student) {
        for (Student existingStudent : school.getStudentsList()) {
            if (existingStudent.getId() == student.getId()) {
                return "Student not added; ID already exists: " + student.toString();
            }
        }
           school.getStudentsList().add(student);
        return "Student added successfully: " + student.toString();
    }

    public String getStudents(String schoolName){
        if ("GerardoInstitute".equalsIgnoreCase(schoolName)) {
            return gerardoInstitute.getStudentsList().toString();
        } else if ("ZetCollege".equalsIgnoreCase(schoolName)) {
            return zetCollege.getStudentsList().toString();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found");
        }
    }

    public String updateStudent(School school, Student student) {
        for (Student existingStudent : school.getStudentsList()) {
            if (existingStudent.getId() == student.getId()) {
                existingStudent.setFirstName(student.getFirstName());
                existingStudent.setLastName((student.getLastName()));
                existingStudent.setAge(student.getAge());
                return "Student update; ID already exists: " + student.toString();
            }
        }
        return "Student does not exist" + student.toString();
    }

    public String getStudentGrades(School school, int id) {
        ArrayList<Integer> gradesStudentList = new ArrayList<>();
        for(Grade gradeElement : school.getGradeList()){
            if(id == gradeElement.getStudent()){
                gradesStudentList.add(gradeElement.getScore());
            }
        }
        return gradesStudentList.toString();
    }
}
