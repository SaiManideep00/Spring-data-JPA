package com.example.spring_data_jpa.repository;

import com.example.spring_data_jpa.entity.Course;
import com.example.spring_data_jpa.entity.Student;
import com.example.spring_data_jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

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


//    @Test
//    public void findAllPagination()
//    {
//        Pageable firstThreeRecordsWithPages=PageRequest.of(0,3);
//        Pageable secondPageWithTwoRecords=PageRequest.of(0,2);
//        List<Course> courses=courseRepository.findAll(firstThreeRecordsWithPages).getContent();
//        Long totalElements=courseRepository.findAll(firstThreeRecordsWithPages).getTotalElements();
//        Integer totalPages=courseRepository.findAll(firstThreeRecordsWithPages).getTotalPages();
//
//        System.out.println(courses);
//        System.out.println(totalElements);
//        System.out.println(totalPages);
//
//    }
//
//
//    @Test
//    public void findAllBySorting()
//    {
//        Pageable sortByTitle=PageRequest.of(0,2,Sort.by("course_title"));
//        Pageable sortByCreditDesc=PageRequest.of(0,2,Sort.by("credits").descending());
//        Pageable sortByCreditsAndTitleDesc=PageRequest.of(0,2,Sort.by("credits").descending()
//                .and(Sort.by("course_title").descending()));
//    }

    @Test
public void testMapping()
{
    Teacher teacher=Teacher.builder()
            .firstName("VK")
            .lastName("GK").build();
    Student student=Student.builder().firstName("Abhishek").lastName("Sharma").emailId("abdhsgsh@gmail.com").build();
    Course course=Course.builder().courseTitle("AI").credits(3).teacher(teacher).build();
    course.addStudents(student);
    courseRepository.save(course);
}

}