package com.example.bootcampproject.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "loancard")
public class LoanCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer loanId;
    @NotBlank(message="Loan type cannot be blank")
    private String loanType;
    @Positive(message= "Duration of loan must be a positive value")
    private Integer duration;
    @OneToOne(mappedBy = "loanCard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EmployeeCardDetails employeeCardDetails; 
    @OneToOne(mappedBy = "loanCard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private LoanTransaction loanTransaction;
    public LoanCard( String loanType, Integer duration){
        this.loanType = loanType;
        this.duration = duration;
    }
}

