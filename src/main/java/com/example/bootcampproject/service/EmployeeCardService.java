package com.example.bootcampproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.dto.EmployeeCardDetailsDTO;
import com.example.bootcampproject.entity.EmployeeCardDetails;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

public interface EmployeeCardService {
    public List < EmployeeCardDetails > getAllEmployeeCardDetails();
    public ResponseEntity < EmployeeCardDetails > getEmployeeCardDetailsById(@PathVariable(value = "id") Long employeeCardDetailsId) throws ResourceNotFoundException;
    public EmployeeCardDetails createEmployeeCardDetails(@Valid @RequestBody EmployeeCardDetails employeeCardDetails);
    // public ResponseEntity < EmployeeCardDetails > updateEmployeeCardDetails(@PathVariable(value = "id") Long employeeCardDetailsId,
    //     @Valid @RequestBody EmployeeCardDetails employeeCardDetails) throws ResourceNotFoundException;
    public Map < String, Boolean > deleteEmployeeCardDetails(@PathVariable(value = "id") Long employeeCardDetailsId)
    throws ResourceNotFoundException;
    public ResponseEntity<EmployeeCardDetails> registerEmployeeCardDetails(@Valid @RequestBody EmployeeCardDetailsDTO employeeCardDetailsDTO)
     throws ResourceNotFoundException;
}

