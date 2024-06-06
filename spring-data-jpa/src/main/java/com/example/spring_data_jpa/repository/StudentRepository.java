package com.example.spring_data_jpa.repository;

import com.example.spring_data_jpa.entity.Student;
import jakarta.transaction.Transactional;
import org.hibernate.collection.internal.StandardIdentifierBagSemantics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByFirstName(String name);
    List<Student> findByLastNameContaining(String name);


    //JPQL
    @Query("select s from Student s where s.emailId=?1")
    Student findByEmailAddress(String email);

    //NAtiveSql
    @Query(value = "SELECT * FROM tbl_student s WHERE s.email_address = ?1", nativeQuery = true)
    Student findByEmailAddressNative(String email);

    //Named Param
    @Query(value="select * from tbl_student s where s.email_address=:emailId",nativeQuery = true)
    Student findByEmailAddressNamedParam(@Param("emailId") String emailId);


    //Update
    @Modifying
    @Transactional
    @Query(value = "update tbl_student set first_name=?1 where email_address=?2",nativeQuery = true)
    int updateFirstNameByEmailAddress(String firstName, String emailAddress);
}
