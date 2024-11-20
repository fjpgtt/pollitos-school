package com.iwaconsolti.school.repository;

import com.iwaconsolti.school.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s WHERE s.school.name = :schoolName")
    List<Student> findAllBySchoolName(@Param("schoolName") String schoolName);

    @Query("SELECT s FROM Student s WHERE s.id = :id AND s.school.name = :schoolName")
    Optional<Student> findByIdAndSchoolName(@Param("id") int id, @Param("schoolName") String schoolName);
}
