package com.example.spring_security.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
//    @SequenceGenerator(name = "user_sequence",
//    sequenceName = "user-sequence",
//    initialValue = 1,
//    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(length = 60)
    private String password;
    private String role;
    private String email;
    private boolean enabled=false;
}
