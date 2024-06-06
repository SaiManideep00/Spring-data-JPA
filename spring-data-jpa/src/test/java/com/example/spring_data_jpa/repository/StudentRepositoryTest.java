package com.example.spring_data_jpa.repository;

import com.example.spring_data_jpa.entity.Guardian;
import com.example.spring_data_jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;


    @Test
    public void saveStudent()
    {
        Guardian guardian=Guardian.builder().email("mkljs@gmial.com").name("Mukesh").mobile("9898982928").build();
        Student student=Student.builder().emailId("example2@gmail.com").firstName("Rishi").lastName("C").guardian(guardian).build();
        studentRepository.save(student);

    }
    @Test
    public void findByFirstName()
    {
        System.out.println(studentRepository.findByFirstName("Rishi"));
    }

    @Test
    public void findByLastNameContaining()
    {
        System.out.println(studentRepository.findByLastNameContaining("C"));
    }
}