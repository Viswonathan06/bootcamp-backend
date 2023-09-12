package com.example.bootcampproject.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message="Name cannot be blank")
    private String employeeName;
    @NotBlank(message="Designation cannot be blank")
    private String designation;
    @NotBlank(message="Department cannot be blank")
    private String department;
    @NotBlank(message="Gender cannot be blank")
    private String gender;
    private String dateOfBirth;
    private String dateOfJoining;
    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EmployeeCardDetails employeeCardDetails;
    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EmployeeIssue employeeIssue;

    public Employee(){

    }
    public Employee(String employeeName,String department,String designation ,String gender ,String dateOfBirth ,String dateOfJoining ,EmployeeCardDetails employeeCardDetails ,EmployeeIssue employeeIssue){
        this.employeeName =  employeeName;
        this.designation= designation;
        this.department =  department;
        this.gender =  gender;
        this.dateOfBirth =  dateOfBirth;
        this.dateOfJoining =  dateOfJoining;
        this.employeeCardDetails=employeeCardDetails;
        this.employeeIssue = employeeIssue;
    }
   
    
}
