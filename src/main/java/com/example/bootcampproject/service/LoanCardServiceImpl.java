
package com.example.bootcampproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.entity.LoanCard;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.repository.LoanCardRepository;

import jakarta.validation.Valid;

@Service
public class LoanCardServiceImpl implements LoanCardService{
    @Autowired
    private LoanCardRepository loanCardRepository;
    public LoanCardServiceImpl(LoanCardRepository loanCardRepository){
        this.loanCardRepository = loanCardRepository;
    }

    @Override
    public List < LoanCard > getAllLoanCard() {
        return loanCardRepository.findAll();
    }
    @Override
    public ResponseEntity < LoanCard > getLoanCardById(@PathVariable(value = "id") Long loanCardId)
    throws ResourceNotFoundException {
        LoanCard loanCard = loanCardRepository.findById(loanCardId)
            .orElseThrow(() -> new ResourceNotFoundException("LoanCard not found for this id :: " + loanCardId));
        return ResponseEntity.ok().body(loanCard);
    }
    @Override
    public LoanCard createLoanCard(@Valid @RequestBody LoanCard loanCard) {
        return loanCardRepository.save(loanCard);
    }
    
    @Override
    public Map < String, Boolean > deleteLoanCard(@PathVariable(value = "id") Long loanCardId)
    throws ResourceNotFoundException {
        LoanCard loanCard = loanCardRepository.findById(loanCardId)
            .orElseThrow(() -> new ResourceNotFoundException("LoanCard not found for this id :: " + loanCardId));

        loanCardRepository.delete(loanCard);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @Override
    public ResponseEntity<String> registerLoanCard(@Valid @RequestBody LoanCard loanCardDetails)
     throws ResourceNotFoundException {
        LoanCard savedLoanCard =  loanCardRepository.save(loanCardDetails);
        return ResponseEntity.status(HttpStatus.OK).body("LoanCard Registered!");
    }
    
}




