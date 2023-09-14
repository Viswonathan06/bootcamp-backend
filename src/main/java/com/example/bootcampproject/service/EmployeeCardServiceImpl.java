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

import com.example.bootcampproject.dto.EmployeeCardDetailsDTO;
import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.entity.EmployeeCardDetails;
import com.example.bootcampproject.entity.LoanCard;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.repository.EmployeeCardRepository;
import com.example.bootcampproject.repository.EmployeeRepository;
import com.example.bootcampproject.repository.LoanCardRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService{
    @Autowired
    private EmployeeCardRepository employeeCardRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LoanCardRepository loanCardRepository;
    

    @Override
    public List < EmployeeCardDetails > getAllEmployeeCardDetails() {
        return employeeCardRepository.findAll();
    }
    @Override
    public ResponseEntity < EmployeeCardDetails > getEmployeeCardDetailsById(@PathVariable(value = "id") Long employeeCardId)
    throws ResourceNotFoundException {
        EmployeeCardDetails employeeCard = employeeCardRepository.findById(employeeCardId)
            .orElseThrow(() -> new ResourceNotFoundException("EmployeeCardDetails not found for this id :: " + employeeCardId));
        return ResponseEntity.ok().body(employeeCard);
    }
    @Override
    public EmployeeCardDetails createEmployeeCardDetails(@Valid @RequestBody EmployeeCardDetails employeeCard) {
        return employeeCardRepository.save(employeeCard);
    }
    
    @Override
    public Map < String, Boolean > deleteEmployeeCardDetails(@PathVariable(value = "id") Long employeeCardId)
    throws ResourceNotFoundException {
        EmployeeCardDetails employeeCard = employeeCardRepository.findById(employeeCardId)
            .orElseThrow(() -> new ResourceNotFoundException("EmployeeCardDetails not found for this id :: " + employeeCardId));

        employeeCardRepository.delete(employeeCard);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @Override
    public ResponseEntity<EmployeeCardDetails> registerEmployeeCardDetails(@Valid @RequestBody EmployeeCardDetailsDTO employeeCardDTO)
     throws ResourceNotFoundException {
        LoanCard loanCard = loanCardRepository.findById(Long.valueOf(employeeCardDTO.getLoanCardID()))
            .orElseThrow(() -> new ResourceNotFoundException("LoanCard not found for this id :: " + employeeCardDTO.getLoanCardID()));
        Employee employee = employeeRepository.findById(Long.valueOf(employeeCardDTO.getEmployeeId()))
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeCardDTO.getEmployeeId()));
        
        EmployeeCardDetails employeeCard = new EmployeeCardDetails(employeeCardDTO.getCardID(),employeeCardDTO.getIssueDate(),
        loanCard,employee);
        EmployeeCardDetails savedEmployeeCardDetails =  employeeCardRepository.save(employeeCard);
        return ResponseEntity.status(HttpStatus.OK).body(savedEmployeeCardDetails);
    }

}




