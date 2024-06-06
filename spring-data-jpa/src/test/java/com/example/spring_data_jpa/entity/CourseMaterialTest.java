package com.example.spring_data_jpa.entity;

import com.example.spring_data_jpa.repository.CourseMaterialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialTest {
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;
    @Test
            public void saveCourseMaterial() {
        Course course = Course.builder().courseTitle("DSA").credits(3).build();
        CourseMaterial courseMaterial = CourseMaterial.builder().url("www.google.com").course(course).build();
       courseMaterialRepository.save(courseMaterial);

    }

}