package com.example.bootcampproject.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class LoanTransactionDTO {
    private Integer transactionId;
    private Date timestamp;
    private Integer amount;
    private Integer loanCardId;
    private Integer employeeId;
    private Integer itemId;
    private Integer duration;
    private String category;
    private Boolean loanOrNot;
    private String status;
    
    public LoanTransactionDTO(String status, Boolean loanOrNot, String category, Integer transactionId,Date timestamp,Integer amount,Integer loanCardId,Integer employeeId,Integer itemId, Integer duration){
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.loanCardId = loanCardId;
        this.employeeId = employeeId;
        this.itemId = itemId;
        this.duration = duration;
        this.category = category;
        this.loanOrNot = loanOrNot;
        this.status = status;
    }
    public LoanTransactionDTO(){

    }
}
