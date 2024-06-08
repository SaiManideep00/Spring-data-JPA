package com.example.spring_security.entity;

import jakarta.persistence.*;
import jakarta.servlet.http.PushBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {

    //Expiration time is 10 minutes
    private static final int EXPIRATION_TIME=10;
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String token;
private Date expirationTime;

@OneToOne(fetch = FetchType.EAGER)
@JoinColumn(
        name="user-id",
        nullable = false,
        foreignKey = @ForeignKey(name = "FK")
)
private User user;

public VerificationToken(User user,String token){
    super();
    this.user=user;
    this.token=token;
    this.expirationTime=calculateExpirationDate(EXPIRATION_TIME);
}
public VerificationToken(String token)
{
    super();
    this.token=token;
    this.expirationTime=calculateExpirationDate(EXPIRATION_TIME);
}
    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expirationTime);
        return new Date(calendar.getTime().getTime());
    }

}
