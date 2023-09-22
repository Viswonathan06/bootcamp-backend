package com.example.bootcampproject.dto;

import java.sql.Date;

import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Table(name = "employee_master")
public class EmployeeDTO {
    private Integer employeeId;
    private String employeeName;
    private String designation;
    private String department;
    private String gender;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfJoining;
    private String userName;   
    private String password;
    private String emailId;
    private String role = "EMPLOYEE";
    private Integer balance;
    private List<Integer> LoanTransactionId; 

    public EmployeeDTO(){

    }
    public EmployeeDTO(Integer balance, Integer employeeId, String userName, String emailId, String password, String employeeName,String department,String designation ,String gender ,Date dateOfBirth ,Date dateOfJoining , List<Integer> LoanTransactionId){
        this.password = password;
        this.userName = userName;
        this.designation= designation;
        this.department =  department;
        this.gender =  gender;
        this.dateOfBirth =  dateOfBirth;
        this.dateOfJoining =  dateOfJoining;
        this.emailId = emailId;
        this.LoanTransactionId = LoanTransactionId;
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.balance = balance;
    }
   
    
}
