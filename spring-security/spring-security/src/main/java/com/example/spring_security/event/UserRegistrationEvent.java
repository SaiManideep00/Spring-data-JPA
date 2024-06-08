package com.example.spring_security.event;

import com.example.spring_security.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter

public class UserRegistrationEvent extends ApplicationEvent {
    private User user;
    private String applicationURL;
    public UserRegistrationEvent(User user, String applicationURL) {
        super(user);
        this.user=user;
        this.applicationURL=applicationURL;
    }
}
