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
public class Course {

    @Id
    @SequenceGenerator(
            name = "course-sequence",
            sequenceName = "course-sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course-sequence"
    )
    private Long courseId;
    private String courseTitle;
    private  Integer credits;


    @OneToOne(
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;
}
