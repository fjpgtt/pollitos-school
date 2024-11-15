package com.iwaconsolti.school.miguel.service;

import com.iwaconsolti.school.miguel.model.Courses;
import com.iwaconsolti.school.miguel.model.School;
import com.iwaconsolti.school.miguel.model.dto.CoursesDTO;
import com.iwaconsolti.school.miguel.model.dto.StudentsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Slf4j
@Service
public class CourseService {

    private final School gerardoInstitute;
    private final School zetCollege;

    public CourseService(@Qualifier("GerardoInstitute") School gerardoInstitute, @Qualifier("ZetCollege") School zetCollege){
        this.gerardoInstitute = gerardoInstitute;
        this.zetCollege = zetCollege;
    }

    public Courses createCourse(String schoolName, CoursesDTO courseDTO) {

        Courses course = new Courses();
        course.setId(courseDTO.getId());
        course.setNameCourse(courseDTO.getNameCourse());
        course.setProfessorName(courseDTO.getProfessorName());

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            gerardoInstitute.getCourses().put(course.getId(), course);
            log.info("The course was successfully registered in Gerardo Institute {}",course);
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            zetCollege.getCourses().put(course.getId(), course);
            log.info("The course was successfully registered in Zet College {}",course);
        }
        return course;
    }

    public Collection<CoursesDTO> getCourses(String schoolName){

        Collection<CoursesDTO> coursesDTOSList = new ArrayList<>();

        if("GerardoInstitute".equalsIgnoreCase(schoolName)){
            for(Courses courses : gerardoInstitute.getCourses().values()){
                CoursesDTO courseDTO = new CoursesDTO();
                courseDTO.setId(courses.getId());
                courseDTO.setNameCourse(courses.getNameCourse());
                courseDTO.setProfessorName(courses.getProfessorName());
                coursesDTOSList.add(courseDTO);
            }
        }else if("ZetCollege".equalsIgnoreCase(schoolName)) {
            for(Courses courses : zetCollege.getCourses().values()){
                CoursesDTO courseDTO = new CoursesDTO();
                courseDTO.setId(courses.getId());
                courseDTO.setNameCourse(courses.getNameCourse());
                courseDTO.setProfessorName(courses.getProfessorName());
                coursesDTOSList.add(courseDTO);
            }
        }
        return coursesDTOSList;
    }

    public Courses editCourse(int id, String schoolName, CoursesDTO courseDTO){

        Courses course = new Courses();
        course.setId(courseDTO.getId());
        course.setNameCourse(courseDTO.getNameCourse());
        course.setProfessorName(courseDTO.getProfessorName());

        if("GerardoInstitute".equals(schoolName)){
            gerardoInstitute.getCourses().put(id,course);
            log.info("The course was edited successfully in Gerardo Institute {}",course);
        }else if("ZetCollege".equals(schoolName)) {
            zetCollege.getCourses().put(id,course);
            log.info("The course was edited successfully in Zet Collage {}",course);

        }
        return course;
    }
}