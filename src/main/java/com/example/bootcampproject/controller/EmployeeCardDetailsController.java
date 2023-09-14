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

import com.example.bootcampproject.dto.EmployeeCardDetailsDTO;
import com.example.bootcampproject.entity.EmployeeCardDetails;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.service.EmployeeCardService;

import jakarta.validation.Valid;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EmployeeCardDetailsController {
    @Autowired
    private EmployeeCardService employeeCardService;
    

    @GetMapping("/employeecard/all")
    public List < EmployeeCardDetailsDTO > getAllEmployeeCardDetails() {
        List <EmployeeCardDetails > cardList =  employeeCardService.getAllEmployeeCardDetails();
        List <EmployeeCardDetailsDTO> finalList = new ArrayList<>();
        for(EmployeeCardDetails employeeCardDetails: cardList){
            EmployeeCardDetailsDTO issueDto = new EmployeeCardDetailsDTO(employeeCardDetails.getId(),employeeCardDetails.getLoanCard().getLoanId(), employeeCardDetails.getEmployee().getEmployeeId(), employeeCardDetails.getIssueDate());
            finalList.add(issueDto);
        }
        return finalList;

    }

    @GetMapping("/employeecard/{id}")
    public ResponseEntity < EmployeeCardDetails > getEmployeeCardDetailsById(@PathVariable(value = "id") Long employeeCardId)
    throws ResourceNotFoundException {
        return employeeCardService.getEmployeeCardDetailsById(employeeCardId);
    }

    @PostMapping("/employeecard/register")
    public ResponseEntity<EmployeeCardDetails> registerEmployeeCardDetails( @Valid @RequestBody EmployeeCardDetailsDTO employeeCardDTO)
    throws ResourceNotFoundException {
        
        return employeeCardService.registerEmployeeCardDetails(employeeCardDTO);
    }

    @PostMapping("/employeecard")
    public EmployeeCardDetails createEmployeeCardDetails(@Valid @RequestBody EmployeeCardDetails employeeCard) {
        return employeeCardService.createEmployeeCardDetails(employeeCard);
    }

    // @PutMapping("/employeeCard/{id}")
    // public ResponseEntity < EmployeeCardDetails > updateEmployeeCardDetails(@PathVariable(value = "id") Long employeeCardId,
    //     @Valid @RequestBody EmployeeCardDetails employeeCardDetails) throws ResourceNotFoundException {
    //     return employeeCardService.updateEmployeeCardDetails(employeeCardId, employeeCardDetails);
    // }

    @DeleteMapping("/employeecard/{id}")
    public Map < String, Boolean > deleteEmployeeCardDetails(@PathVariable(value = "id") Long employeeCardId)
    throws ResourceNotFoundException {
        return employeeCardService.deleteEmployeeCardDetails(employeeCardId);
    }
}
