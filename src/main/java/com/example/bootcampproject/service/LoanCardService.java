package com.example.bootcampproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.entity.LoanCard;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

public interface LoanCardService {
    public List < LoanCard > getAllLoanCard();
    public ResponseEntity < LoanCard > getLoanCardById(@PathVariable(value = "id") Long loanCardId) throws ResourceNotFoundException;
    public LoanCard createLoanCard(@Valid @RequestBody LoanCard loanCard);
    // public ResponseEntity < LoanCard > updateLoanCard(@PathVariable(value = "id") Long loanCardId,
    //     @Valid @RequestBody LoanCard loanCard) throws ResourceNotFoundException;
    public Map < String, Boolean > deleteLoanCard(@PathVariable(value = "id") Long loanCardId)
    throws ResourceNotFoundException;
    public ResponseEntity<String> registerLoanCard(@Valid @RequestBody LoanCard loanCard)
     throws ResourceNotFoundException;
}
