package com.example.bootcampproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.dto.LoanTransactionDTO;
import com.example.bootcampproject.entity.LoanTransaction;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

public interface LoanTransactionService {
    public List < LoanTransactionDTO > getAllLoanTransaction();
    public ResponseEntity<List < LoanTransactionDTO >> getLoanTransactionByEmployeeId(Integer employeeId) ;
    public ResponseEntity < LoanTransaction > getLoanTransactionById(@PathVariable(value = "id") Long loanTransactionId) throws ResourceNotFoundException;
    public LoanTransaction createLoanTransaction(@Valid @RequestBody LoanTransaction loanTransaction);
    // public ResponseEntity < LoanTransaction > updateLoanTransaction(@PathVariable(value = "id") Long loanTransactionId,
    //     @Valid @RequestBody LoanTransaction loanTransaction) throws ResourceNotFoundException;
    public Map < String, Boolean > deleteLoanTransaction(@PathVariable(value = "id") Long loanTransactionId)
    throws ResourceNotFoundException;
    public ResponseEntity<LoanTransaction> registerLoanTransaction(@Valid @RequestBody LoanTransactionDTO loanTransactionDTO)
     throws ResourceNotFoundException;
     public ResponseEntity<LoanTransaction> registerLoanTransaction(@Valid @RequestBody LoanTransactionDTO loanTransactionDTO, Boolean loanOrNot)
     throws ResourceNotFoundException;
}
