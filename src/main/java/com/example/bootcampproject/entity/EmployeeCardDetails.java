package com.example.bootcampproject.entity;
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
    private Integer issueDate;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "loan_card_id")
    private LoanCard loanCard;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public EmployeeCardDetails(){

    }
    public EmployeeCardDetails(Integer Id, Integer issueDate, LoanCard loanCard, Employee employee){
        
    }
    

}
