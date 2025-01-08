package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.persistence.model.Courses;
import com.iwaconsolti.school.miguel.persistence.model.School;
import com.iwaconsolti.school.miguel.persistence.repository.CourseRepository;
import com.iwaconsolti.school.miguel.persistence.repository.SchoolRepository;
import com.iwaconsolti.school.miguel.persistence.repository.UnionSchoolStudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CourseService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UnionSchoolStudentRepository unionSchoolStudentRepository;


    private final School gerardoInstitute;
    private final School zetCollege;

    public CourseService(@Qualifier("GerardoInstitute") School gerardoInstitute, @Qualifier("ZetCollege") School zetCollege){
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public Courses createCourse(String schoolName, Courses course) {
        School school = schoolRepository.findByName(schoolName);
        course.setSchoolId(school.getId());
        return  courseRepository.save(course);
    }

    public List<Courses> getCourses(String schoolName){
        School school = schoolRepository.findByName(schoolName);
        return courseRepository.findAllCoursesBySchoolId(school.getId());
    }

    public Courses editCourse(String schoolName, Courses course){
        School school = schoolRepository.findByName(schoolName);
        if(school != null){
            course.setSchoolId(school.getId());
            return courseRepository.save(course);
        }
        return null;
    }
}