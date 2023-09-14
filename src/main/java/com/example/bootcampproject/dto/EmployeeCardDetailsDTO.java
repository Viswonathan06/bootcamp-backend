package com.example.bootcampproject.dto;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class EmployeeCardDetailsDTO {
    private Integer cardID;
    private Integer employeeId;
    private Integer loanCardID;
    private Date issueDate;

    public EmployeeCardDetailsDTO(Integer cardID, Integer employeeId,Integer loanCardID,Date issueDate){
        this.cardID = cardID;
        this.employeeId = employeeId;
        this.loanCardID = loanCardID;
        this.issueDate = issueDate;
    }
}
