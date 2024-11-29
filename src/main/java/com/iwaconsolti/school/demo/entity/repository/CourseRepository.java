package com.iwaconsolti.school.demo.entity.repository;

import com.iwaconsolti.school.demo.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    //USO DE QUERYS
    @Query("select c from CourseEntity c where c.name = ?1")
    public List<CourseEntity> findByName(String name);

}
