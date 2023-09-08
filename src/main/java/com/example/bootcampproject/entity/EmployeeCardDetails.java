package com.example.bootcampproject.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "employee_card")
public class EmployeeCardDetails {
    private String empId;
    private Integer issueDate;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_card_id")
    private LoanCard loanCard;


    public EmployeeCardDetails(String empId, Integer issueDate, LoanCard loanCard) {
        this.empId = empId;
        this.issueDate = issueDate;
        this.loanCard = loanCard;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Integer getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Integer issueDate) {
        this.issueDate = issueDate;
    }

    

    

}
