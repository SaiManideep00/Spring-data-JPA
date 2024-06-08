package com.example.spring_security.service;

import com.example.spring_security.entity.User;
import com.example.spring_security.entity.VerificationToken;
import com.example.spring_security.model.UserModel;
import com.example.spring_security.repository.UserRepository;
import com.example.spring_security.repository.VerificationTokenRepository;
import org.hibernate.Cache;
import org.springframework.beans.factory.annotation.Autowired;
//import com.example.spring_security.config.WebSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
   private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public User registerUser(UserModel userModel) {
        User user=User.builder().email(userModel.getEmail()).firstName(userModel.getFirstName())
                .lastName(userModel.getLastName()).role("User")
                .password(passwordEncoder.encode(userModel.getPassword())).build();
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationToken(String token, User user) {
        VerificationToken verificationToken=new VerificationToken(user,token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken=verificationTokenRepository.findByToken(token);
        if(verificationToken==null)
            return "invalid";
        User user=verificationToken.getUser();
        Calendar calendar=Calendar.getInstance();
        if(verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <=0) {
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public VerificationToken generateNewVerficationToken(String oldToken) {
        VerificationToken verificationToken=verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);

        return verificationToken;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {

    }
}
