package com.example.bootcampproject.dto;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class EmployeeIssueDTO {
        private Integer issueId;
        private Integer item_id;
        private Integer employee_id;
        private Date issueDate;
        private Date returnDate;


    public EmployeeIssueDTO(Integer issueId,Integer item_id,Integer employee_id,Date issueDate,Date returnDate){
        this.issueId =issueId;
        this.item_id =item_id;
        this.employee_id =employee_id;
        this.issueDate =issueDate;
        this.returnDate =returnDate;
    }
    public EmployeeIssueDTO(){
        
    }
}
