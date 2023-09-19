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
        return loanTransactionService.getAllLoanTransaction();
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity < LoanTransaction > getLoanTransactionById(@PathVariable(value = "id") Long loanTransactionId)
    throws ResourceNotFoundException {
        return loanTransactionService.getLoanTransactionById(loanTransactionId);
    }
    @GetMapping("/transaction/employee/{id}")
    public ResponseEntity<List<LoanTransactionDTO>> getLoanTransactionByEmployeeId(@PathVariable(value = "id") Integer loanTransactionId)
    throws ResourceNotFoundException {
        return loanTransactionService.getLoanTransactionByEmployeeId(loanTransactionId);
    }


    @PostMapping("/transaction/create")
    public ResponseEntity<LoanTransaction> registerLoanTransaction( @Valid @RequestBody LoanTransactionDTO loanTransactionDTO)
    throws ResourceNotFoundException {
        if(loanTransactionDTO.getItemId() == null){
            return loanTransactionService.registerLoanTransaction(loanTransactionDTO);
        }
        return loanTransactionService.registerLoanTransaction(loanTransactionDTO, true);
    }

    // @PostMapping("/loan/create")
    // public ResponseEntity<LoanTransaction> registerLoan( @Valid @RequestBody LoanTransactionDTO loanTransactionDTO)
    // throws ResourceNotFoundException {
        
    // }

    // @PostMapping("/transaction")
    // public LoanTransaction createLoanTransaction(@Valid @RequestBody LoanTransaction loanTransaction) {
    //     return loanTransactionService.createLoanTransaction(loanTransaction, true);
    // }

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
