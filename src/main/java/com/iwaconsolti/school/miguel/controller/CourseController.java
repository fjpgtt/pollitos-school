package com.iwaconsolti.school.miguel.controller;

import com.iwaconsolti.school.miguel.persistence.model.Courses;
import com.iwaconsolti.school.miguel.persistence.model.School;
import com.iwaconsolti.school.miguel.persistence.model.Students;
import com.iwaconsolti.school.miguel.persistence.model.dto.CoursesDTO;
import com.iwaconsolti.school.miguel.persistence.repository.SchoolRepository;
import com.iwaconsolti.school.miguel.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/app/{nameSchool}/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SchoolRepository schoolRepository;

    @PostMapping("/")
    public ResponseEntity<Object> newCourse(@PathVariable String nameSchool, @RequestBody CoursesDTO courseDTO) {

        School school = schoolRepository.findByName(nameSchool);

        if(school == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message", "the school was not found",
                            "status", HttpStatus.NOT_FOUND
                    )
            );
        }

        if (courseDTO.getNameCourse() == null || courseDTO.getProfessorName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "message","Required data is missing", "stautus", HttpStatus.BAD_REQUEST
                    )
            );
        }
        Courses objCourse = convertToEntity(courseDTO);
        courseService.createCourse(nameSchool,objCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message", "The Course registered successfully"
                )
        );
    }

    @GetMapping("/")
    public ResponseEntity<Object> listCourse(@PathVariable String nameSchool) {

        List<Courses>  courses = courseService.getCourses(nameSchool);

        if(courses.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message","there are no registered courses","status",HttpStatus.NOT_FOUND
                    )
            );
        }

        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> editCourse(@PathVariable String nameSchool,@PathVariable int courseId, @RequestBody CoursesDTO coursesDTO){

        if(coursesDTO.getNameCourse() == null || coursesDTO.getProfessorName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("message", "Required data is missing", "status", HttpStatus.BAD_REQUEST)
            );
        }
        Courses objCourses = convertToEntity(coursesDTO);
        Courses updateCourse = courseService.editCourse(nameSchool, objCourses);

        if(updateCourse == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "message","The course to modify was not found","status", HttpStatus.NOT_FOUND
                    )
            );
        }

        return ResponseEntity.ok(
                Map.of(
                        "message", "The course was updated successfully", "status", HttpStatus.ACCEPTED
                )
        );
    }

    protected CoursesDTO convertToDto(Courses entity){
        return new CoursesDTO(entity);
    }

    protected Courses convertToEntity(CoursesDTO dto){
        Courses course = new Courses(dto);
        if(!Objects.isNull(dto.getId())){
            course.setId(dto.getId());
        }
        return course;
    }
}