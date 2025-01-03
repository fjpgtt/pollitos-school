package com.iwaconsolti.school.demo.service;

import com.iwaconsolti.school.demo.entity.CourseEntity;
import com.iwaconsolti.school.demo.entity.StudentEntity;
import com.iwaconsolti.school.demo.entity.repository.StudentRepository;
import com.iwaconsolti.school.demo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class StudentService implements StudentInterface{

    //H2
    @Autowired
    StudentRepository studentRepository;

    private final List<Student> students = new ArrayList<>();

    public StudentService() {
        Student eduardo = Student.builder().id(1).firstName("Eduardo").lastName("Gonzalez").age(24).creationDate(new Date()).build();
        Student gerardo = Student.builder().id(2).firstName("Gerardo").lastName("Gonzalez").age(20).creationDate(new Date()).build();
        Student claudia = Student.builder().id(3).firstName("Claudia").lastName("Vazquez").age(47).creationDate(new Date()).build();
        Student emmanuel = Student.builder().id(4).firstName("Gerardo").lastName("Gonzalez").age(44).creationDate(new Date()).build();

        this.students.add(eduardo);
        this.students.add(gerardo);
        this.students.add(claudia);
        this.students.add(emmanuel);
    }

    //H2
    @Override
    public List<StudentEntity> findAllStudents(){
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity saveStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    @Override
    public StudentEntity updateStudent(Integer id, StudentEntity studentEntity) {
        StudentEntity studentEntityDB = studentRepository.findById(id).get();
        if(Objects.nonNull(studentEntity.getAge()) && !"".equalsIgnoreCase(String.valueOf(studentEntity.getAge()))){
            studentEntityDB.setAge(studentEntity.getAge());
        }

        if(Objects.nonNull(studentEntity.getFirstName()) && !"".equalsIgnoreCase(studentEntity.getFirstName())){
            studentEntityDB.setFirstName(studentEntity.getFirstName());
        }

        if(Objects.nonNull(studentEntity.getLastName()) && !"".equalsIgnoreCase(studentEntity.getLastName())){
            studentEntityDB.setLastName(studentEntity.getLastName());
        }

        return studentRepository.save(studentEntityDB);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    public List<Student> getStudents() {
        return this.students;
    }

    public Student getById(int id) {
        log.info("Id: {}", id);
        List<Student> students = this.students.stream().filter(student -> student.getId() == id).toList();
        log.info("Students: {}", students);
        if(!students.isEmpty())
            return students.getFirst();
        return null;
    }

    public Student createStudent(Student student){
        if(student != null){
            student.setCreationDate(new Date());
            this.students.add(student);
        }
        return student;
    }

    public Student update(Student student){
        this.students.stream().forEach(s -> {
          if (s.getId() == student.getId()){
            s.setFirstName(student.getFirstName());
            s.setLastName(student.getLastName());
            s.setAge(student.getAge());
          }
        });
        return this.students.stream().filter(s -> s.getId() == student.getId()).toList().getFirst();
    }
}
