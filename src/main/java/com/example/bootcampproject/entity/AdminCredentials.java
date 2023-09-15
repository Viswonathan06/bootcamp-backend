package com.example.bootcampproject.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message="Username cannot be empty")
    @Size(min=7,max=7,message="Username must be of 7 characters only")
    private String userName;
    @Column( nullable = false)
    @NotBlank(message="Password cannot be empty")
    @Size(min=5, max=20, message="Password must be of 5-20 characters only")
    private String password;

    @Email(message="Invalid Email Address")
    private String emailId;
    @NotBlank(message="Must be of a specific role")
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
