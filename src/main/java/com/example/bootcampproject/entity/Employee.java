package com.example.bootcampproject.entity;
import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
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
    // @NotBlank(message="Name cannot be blank")
    private String employeeName;
    // @NotBlank(message="Designation cannot be blank")
    private String designation;
    // @NotBlank(message="Department cannot be blank")
    private String department;
    // @NotBlank(message="Gender cannot be blank")
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
    private String role = "EMPLOYEE";
    @Column(nullable=false)
    @Min(value = 0)
    private Integer balance = 0;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EmployeeCardDetails employeeCardDetails;
    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EmployeeIssue employeeIssue;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "employee_transaction")
    private List<LoanTransaction> LoanTransaction; 

    public Employee(){

    }
    public Employee(Integer Id, String userName, String emailId, String password, String employeeName,String department,String designation ,String gender ,Date dateOfBirth ,Date dateOfJoining ,EmployeeCardDetails employeeCardDetails ,EmployeeIssue employeeIssue){
        this.employeeId = Id;
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
    }
   
    
}
