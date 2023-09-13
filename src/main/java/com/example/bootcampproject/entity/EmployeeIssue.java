package com.example.bootcampproject.entity;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee_issue")
public class EmployeeIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer issueId;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "item_id")
    private Item item;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date issueDate;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date returnDate;
    public EmployeeIssue(){
        
    }
    public EmployeeIssue(Integer issueId,Item item,Employee employee,Date issueDate,Date returnDate){
        this.issueId = issueId;
        this.item = item;
        this.employee = employee;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
}
