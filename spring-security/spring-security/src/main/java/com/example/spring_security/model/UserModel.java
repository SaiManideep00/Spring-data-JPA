package com.example.spring_security.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private String firstName;
    private String lastName;

    private String password;
    private String matchingPassword;
    //private String role;
    private String email;
}
