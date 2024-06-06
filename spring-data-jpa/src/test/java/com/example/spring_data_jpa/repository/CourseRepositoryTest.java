package com.example.spring_data_jpa.repository;

import com.example.spring_data_jpa.entity.Course;
import com.example.spring_data_jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CourseRepositoryTest {

    @Autowired
private CourseRepository courseRepository;

    @Test
    public void displayCourseDetails()
    {
        System.out.println(courseRepository.findAll());
    }

    @Test
    public void saveCourseWithTeacher()
    {
        Teacher teacher=Teacher.builder()
                .firstName("Mukesh")
                .lastName("Rishi")
                .build();
        Course course=Course.builder()
                .courseTitle("Python")
                .credits(4)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }
}