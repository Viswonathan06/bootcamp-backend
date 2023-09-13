package com.example.bootcampproject.entity;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee_master")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeId;
    @Column(nullable = false)    
    private String employeeName;
    private String designation;
    private String department;
    private String gender;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfJoining;
    @Column(nullable = false)
    private String userName;   
    @Column(nullable = false)
    private String password;
    private String emailId;
    private String role;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EmployeeCardDetails employeeCardDetails;
    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EmployeeIssue employeeIssue;

    public Employee(){

    }
    public Employee(String userName, String emailId, String password, String employeeName,String department,String designation ,String gender ,Date dateOfBirth ,Date dateOfJoining ,EmployeeCardDetails employeeCardDetails ,EmployeeIssue employeeIssue){
        this.employeeName =  employeeName;
        this.password = password;
        this.userName = userName;
        this.designation= designation;
        this.department =  department;
        this.gender =  gender;
        this.dateOfBirth =  dateOfBirth;
        this.dateOfJoining =  dateOfJoining;
        this.employeeCardDetails=employeeCardDetails;
        this.employeeIssue = employeeIssue;
        this.emailId = emailId;
        this.role = null;

    }
   
    
}
