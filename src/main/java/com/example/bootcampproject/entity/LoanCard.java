package com.example.bootcampproject.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "loancard")
public class LoanCard {
    @Id
    private String loanId;
    private String loanType;
    private Integer duration;
    @OneToOne(mappedBy = "loanCard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EmployeeCardDetails employeeCardDetails;

    
}
