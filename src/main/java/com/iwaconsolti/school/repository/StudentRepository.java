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

    @Query("SELECT s FROM Student s WHERE s.school.id = :schoolId")
    List<Student> findAllBySchoolId(@Param("schoolId") int schoolId);

    @Query("SELECT s FROM Student s WHERE s.id = :id AND s.school.id = :schoolId")
    Optional<Student> findByIdAndSchoolId(@Param("id") int id, @Param("schoolId") int schoolId);
}
