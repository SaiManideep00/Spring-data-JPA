package com.example.spring_security.controller;


import com.example.spring_security.entity.VerificationToken;
import com.example.spring_security.event.UserRegistrationEvent;
import com.example.spring_security.model.PasswordModel;
import com.example.spring_security.model.UserModel;
import com.example.spring_security.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import com.example.spring_security.entity.User;

import java.util.UUID;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;
    @PostMapping("registration")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user=userService.registerUser(userModel);
        publisher.publishEvent(new UserRegistrationEvent(user,applicationURL(request)));
        return "Successfully registered";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token)
    {
        String result=userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid"))
            return "User Verified Successfully";
        return "Bad User";
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken  , HttpServletRequest request){
        VerificationToken verificationToken=userService.generateNewVerficationToken(oldToken);
        User user=verificationToken.getUser();
        resendVerificationEmail(user,applicationURL(request),verificationToken);
        return "Verification Link resent";

    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel)
    {
        User user=userService.findByEmail(passwordModel.getEmail());
        if(user!=null){
            String token= UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user,token);

        }
        return "Password reset";
    }

    private void resendVerificationEmail(User user, String s, VerificationToken verificationToken) {

        String url=s+"/verifyRegistration?token="+verificationToken.getToken();
        log.info("Click the link to verify your account:{}",url);
    }


    private String applicationURL(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}
