package com.example.spring_security.event.listener;

import com.example.spring_security.entity.User;
import com.example.spring_security.event.UserRegistrationEvent;
import com.example.spring_security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<UserRegistrationEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        //Create verification token for the user with link
        User user=event.getUser();
        String token= UUID.randomUUID().toString();
        userService.saveVerificationToken(token,user);
        //send mail to user
        String url=event.getApplicationURL()+"/verifyRegistration?token="+token;
        log.info("Click the link to verify your account:{}",url);

    }
}
