package com.example.bootcampproject.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityScan
public class AdminCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column( nullable = false)
    private String password;
    private String emailId;
    private String role = "ADMIN";


    public AdminCredentials(long id,String username,String password,String emailId){
        this.id = id;
        this.userName = username;
        this.password = password;
        this.emailId = emailId;
        
    }
    public AdminCredentials(){
        
    }

}
