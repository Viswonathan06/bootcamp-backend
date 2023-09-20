package com.example.bootcampproject.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "loan_transaction")
public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;
    @Basic(optional = false)
    @Column(name = "trans_time")
    private Date timestamp;
    @Column(name = "amount", nullable = false)
    private Integer amount;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "loan_card_id")
    private LoanCard loanCard;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;
    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "item_id")
    private Item item;
    public LoanTransaction(){

    }
    public LoanTransaction(Integer transactionId,Date timestamp,Integer amount,LoanCard loanCard,Employee employee,Item item){
        this.transactionId = transactionId;
        this.timestamp = new Date();
        this.amount = amount;
        this.loanCard = loanCard;
        this.employee = employee;
        this.item = item;
    }
}
