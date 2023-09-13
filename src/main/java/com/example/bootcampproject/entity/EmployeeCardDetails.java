package com.example.bootcampproject.entity;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee_card")
public class EmployeeCardDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date issueDate;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "loan_card_id")
    private LoanCard loanCard;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public EmployeeCardDetails(){

    }
    public EmployeeCardDetails(Integer Id, Date issueDate, LoanCard loanCard, Employee employee){
        
    }
    

}
