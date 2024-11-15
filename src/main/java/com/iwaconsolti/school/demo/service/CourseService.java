package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.model.Course;
import com.iwaconsolti.school.demo.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CourseService{
        private final School gerardoInstitute;
        private final School zetCollege;

        @Autowired
        public CourseService(@Qualifier("gerardoInstitute") School gerardoInstitute, @Qualifier("zetCollege") School zetCollege) {
            this.gerardoInstitute = gerardoInstitute;
            this.zetCollege = zetCollege;
        }

    public static String deleteGradesCourse(School school, int id) {
        for (Course existingCourse : school.getCourseList()) {
            if (existingCourse.getId() == id) {
                boolean removed = school.getGradeList().removeIf(gradeElement -> gradeElement.getCourse() == id); // Elimina todas las calificaciones del estudiante con el ID dado
                if (removed) {
                    return "All grades deleted for course; ID " + id + " | " + existingCourse.toString();
                } else {
                    return "No grades found for course ID " + id;
                }
            }
        }
        return "Student does not exist: " + id;
    }

    public String createCourse(School school, Course course) {
            for (Course existingCourse : school.getCourseList()) {
                if (existingCourse.getId() == course.getId()) {
                    return "Course not added; ID already exists: " + course.toString();
                }
            }
            school.getCourseList().add(course);
            return "Course added successfully: " + course.toString();
        }

    public String getCourses(String schoolName){
        if ("GerardoInstitute".equalsIgnoreCase(schoolName)) {
            return gerardoInstitute.getCourseList().toString();
        } else if ("ZetCollege".equalsIgnoreCase(schoolName)) {
            return zetCollege.getCourseList().toString();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found");
        }
    }

    public String updateCourse(School school, Course course) {
        for (Course existingCourse : school.getCourseList()) {
            if (existingCourse.getId() == course.getId()) {
                existingCourse.setName(course.getName());
                existingCourse.setProfessorName(course.getProfessorName());
                return "Course update; ID already exists: " + course.toString();
            }
        }
        return "Course does not exist" + course.toString();
    }
}