package com.evan.repository;

import com.evan.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    List<Teacher> findByNameNotNullOrderByAgeAsc();
}
