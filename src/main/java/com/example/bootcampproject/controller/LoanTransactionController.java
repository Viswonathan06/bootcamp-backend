package com.example.bootcampproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bootcampproject.dto.LoanTransactionDTO;
import com.example.bootcampproject.entity.LoanTransaction;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.service.LoanTransactionService;

import jakarta.validation.Valid;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LoanTransactionController {
    @Autowired
    private LoanTransactionService loanTransactionService;
    

    @GetMapping("/transaction/all")
    public List < LoanTransactionDTO > getAllLoanTransaction() {
        List <LoanTransaction > transactionList =  loanTransactionService.getAllLoanTransaction();
        List <LoanTransactionDTO> finalList = new ArrayList<>();
        for(LoanTransaction transaction: transactionList){
            LoanTransactionDTO transactionDto = new LoanTransactionDTO(transaction.getTransactionId(), transaction.getTimestamp(), transaction.getAmount(), transaction.getLoanCard().getLoanId(), transaction.getEmployee().getEmployeeId(),  transaction.getItem().getItemId(), transaction.getLoanCard().getDuration());
            finalList.add(transactionDto);
        }
        return finalList;

    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity < LoanTransaction > getLoanTransactionById(@PathVariable(value = "id") Long loanTransactionId)
    throws ResourceNotFoundException {
        return loanTransactionService.getLoanTransactionById(loanTransactionId);
    }

    @PostMapping("/transaction/register")
    public ResponseEntity<LoanTransaction> registerLoanTransaction( @Valid @RequestBody LoanTransactionDTO loanTransactionDTO)
    throws ResourceNotFoundException {
        
        return loanTransactionService.registerLoanTransaction(loanTransactionDTO);
    }

    @PostMapping("/transaction")
    public LoanTransaction createLoanTransaction(@Valid @RequestBody LoanTransaction loanTransaction) {
        return loanTransactionService.createLoanTransaction(loanTransaction);
    }

    // @PutMapping("/loanTransaction/{id}")
    // public ResponseEntity < LoanTransaction > updateLoanTransaction(@PathVariable(value = "id") Long loanTransactionId,
    //     @Valid @RequestBody LoanTransaction loanTransactionDetails) throws ResourceNotFoundException {
    //     return loanTransactionService.updateLoanTransaction(loanTransactionId, loanTransactionDetails);
    // }

    @DeleteMapping("/transaction/{id}")
    public Map < String, Boolean > deleteLoanTransaction(@PathVariable(value = "id") Long loanTransactionId)
    throws ResourceNotFoundException {
        return loanTransactionService.deleteLoanTransaction(loanTransactionId);
    }
}
