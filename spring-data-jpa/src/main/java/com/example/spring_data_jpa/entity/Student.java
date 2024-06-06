package com.example.spring_data_jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_student",
uniqueConstraints = @UniqueConstraint(name = "email_id_unique",columnNames = "email_address"))
public class Student {
    @Id
    @SequenceGenerator(
            name = "student-sequence",
            sequenceName = "student-sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student-sequence"
    )
    private Long studentId;
    private String firstName;
    private String lastName;
    @Column(name = "email_address",
            nullable = false
    )
    private String emailId;

    @Embedded
   private Guardian guardian;



}
