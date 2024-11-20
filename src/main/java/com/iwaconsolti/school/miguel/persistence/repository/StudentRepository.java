package com.iwaconsolti.school.miguel.persistence.repository;

import com.iwaconsolti.school.miguel.persistence.model.Students;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Students, Long> {

    List<Students> findById(int id);

    @Query(value = "SELECT s.* FROM students s " +
            " INNER JOIN union_school_students uss ON uss.student_id = s.id " +
            " WHERE uss.school_id = :schoolId", nativeQuery = true)
    List<Students> findAllStudents(@Param("schoolId") int schoolId);

    Students save(Students student);

    @Modifying
    @Transactional
    @Query(value = "UPDATE students s SET s.first_name = :firstName, s.last_name = :lastName, s.age = :age WHERE s.id = :id ", nativeQuery = true)
    int UpdateStudent(@Param("id") int id,
                           @Param("firstName") String firstName,
                           @Param("lastName") String lastName,
                           @Param("age") int age);
}
