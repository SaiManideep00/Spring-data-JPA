package com.example.spring_data_jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher-sequence",
            sequenceName = "teacher-sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher-sequence"
    )
    private Long teacherId;
    private String firstName;
    private String lastName;


    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
            @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "teacherId"
            )
    List<Course> courses;
}
