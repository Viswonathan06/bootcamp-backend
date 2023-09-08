package com.example.bootcampproject.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "loancard")
public class LoanCard {

    private String loanId;
    private String loanType;
    private Integer duration;
    @OneToOne(mappedBy = "idCard")
    private EmployeeCardDetails employeeCardDetails;

    public LoanCard() {

    }

    public LoanCard(String loanId, String loanType, Integer duration) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.duration = duration;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return loanId;
    }
    public void setId(String id) {
        this.loanId = id;
    }

    @Column(name = "loanId", nullable = false)
    public String getloanId() {
        return loanId;
    }
    public void setloanId(String loanId) {
        this.loanId = loanId;
    }

    @Column(name = "loanType", nullable = false)
    public String getloanType() {
        return loanType;
    }
    public void setloanType(String loanType) {
        this.loanType = loanType;
    }

    @Column(name = "duration", nullable = false)
    public Integer getduration() {
        return duration;
    }
    public void setduration(Integer duration) {
        this.duration = duration;
    }
}
