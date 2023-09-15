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

    public LoanTransactionDTO(Integer transactionId,Date timestamp,Integer amount,Integer loanCardId,Integer employeeId,Integer itemId, Integer duration){
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.loanCardId = loanCardId;
        this.employeeId = employeeId;
        this.itemId = itemId;
        this.duration = duration;
    }
    public LoanTransactionDTO(){

    }
}
