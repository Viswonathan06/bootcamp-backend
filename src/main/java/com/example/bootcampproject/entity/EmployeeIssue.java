package com.example.bootcampproject.entity;
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
    private String issueDate;
    @Column(nullable = false)
    private String returnDate;
}
