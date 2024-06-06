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
        Guardian guardian=Guardian.builder().email("nkjlgfh@gmial.com").name("Ambani").mobile("9898983934").build();
        Student student=Student.builder().emailId("example4@gmail.com").firstName("Aaksh").lastName("amadf").guardian(guardian).build();
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

    @Test
    public  void findByEmailAddress()
    {
        System.out.println(studentRepository.findByEmailAddress("example4@gmail.com"));
    }

    @Test
    public  void findByEmailAddressNativeSQL()
    {
        System.out.println(studentRepository.findByEmailAddressNative("example4@gmail.com"));
    }

    @Test
    public  void findByEmailAddressNativeSQLParam()
    {
        System.out.println(studentRepository.findByEmailAddressNamedParam("example4@gmail.com"));
    }
}