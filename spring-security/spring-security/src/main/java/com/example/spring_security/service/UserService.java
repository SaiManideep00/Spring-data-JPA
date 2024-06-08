package com.example.spring_security.service;

import com.example.spring_security.entity.User;
import com.example.spring_security.entity.VerificationToken;
import com.example.spring_security.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    User registerUser(UserModel userModel);

    void saveVerificationToken(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerficationToken(String oldToken);

    User findByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);
}
