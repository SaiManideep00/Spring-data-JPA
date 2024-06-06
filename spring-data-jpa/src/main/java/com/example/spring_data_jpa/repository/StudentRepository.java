package com.example.spring_data_jpa.repository;

import com.example.spring_data_jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByFirstName(String name);
    List<Student> findByLastNameContaining(String name);
}
