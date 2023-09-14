
package com.example.bootcampproject.controller;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

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

import com.example.bootcampproject.entity.LoanCard;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.service.LoanCardService;
// @CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LoanCardController {
    @Autowired
    private LoanCardService loanCardService;

    @GetMapping("/loancard/all")
    public List < LoanCard > getAllLoanCard() {
        return loanCardService.getAllLoanCard();
    }

    @GetMapping("/loancard/{id}")
    public ResponseEntity < LoanCard > getLoanCardById(@PathVariable(value = "id") Long loanCardId)
    throws ResourceNotFoundException {
        return loanCardService.getLoanCardById(loanCardId);
    }


    @PostMapping("/loancard/create")
    public ResponseEntity<String> registerLoanCard( @Valid @RequestBody LoanCard loanCard)
    throws ResourceNotFoundException {
        return loanCardService.registerLoanCard(loanCard);
    }

    @PostMapping("/loancard")
    public LoanCard createLoanCard(@Valid @RequestBody LoanCard loanCard) {
        return loanCardService.createLoanCard(loanCard);
    }

    // @PutMapping("/loancard/{id}")
    // public ResponseEntity < LoanCard > updateLoanCard(@PathVariable(value = "id") Long loanCardId,
    //     @Valid @RequestBody LoanCard loanCardDetails) throws ResourceNotFoundException {
    //     return loanCardService.updateLoanCard(loanCardId, loanCardDetails);
    // }
 
    @DeleteMapping("/loancard/{id}")
    public Map < String, Boolean > deleteLoanCard(@PathVariable(value = "id") Long loanCardId)
    throws ResourceNotFoundException {
        return loanCardService.deleteLoanCard(loanCardId);
    }
}
