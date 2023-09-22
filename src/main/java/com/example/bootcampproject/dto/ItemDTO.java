package com.example.bootcampproject.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ItemDTO {
    private Integer itemId;
    private String itemDescription;

    private String issueStatus;
    private String itemMake;

    private String itemCategory;
    private Integer itemValuation;
    private List<Integer> LoanTransactionId; 

    public ItemDTO(){

    }
    
    public ItemDTO(Integer itemId,String itemDescription,String issueStatus,String itemMake,String itemCategory,Integer itemValuation,List<Integer> LoanTransactionId){
        this. itemId = itemId;
        this. itemDescription = itemDescription;
        this. issueStatus = issueStatus;
        this. itemMake = itemMake;
        this. itemCategory = itemCategory;
        this. itemValuation = itemValuation;
        this. LoanTransactionId = LoanTransactionId;
    }
}
