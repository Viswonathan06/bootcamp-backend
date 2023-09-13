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

import com.example.bootcampproject.dto.EmployeeIssueDTO;
import com.example.bootcampproject.entity.EmployeeIssue;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.service.EmployeeIssueService;

import jakarta.validation.Valid;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EmployeeIssueController {
    @Autowired
    private EmployeeIssueService employeeIssueService;

    @GetMapping("/employeeissue/all")
    public List < EmployeeIssueDTO > getAllEmployeeIssue() {
        List <EmployeeIssue > issueList =  employeeIssueService.getAllEmployeeIssue();
        List <EmployeeIssueDTO> finalList = new ArrayList<>();
        for(EmployeeIssue issue: issueList){
            EmployeeIssueDTO issueDto = new EmployeeIssueDTO(issue.getIssueId(), issue.getItem().getItemId(), issue.getEmployee().getEmployeeId(), issue.getIssueDate(), issue.getReturnDate());
            finalList.add(issueDto);
        }
        return finalList;

    }

    @GetMapping("/employeeissue/{id}")
    public ResponseEntity < EmployeeIssue > getEmployeeIssueById(@PathVariable(value = "id") Long employeeIssueId)
    throws ResourceNotFoundException {
        return employeeIssueService.getEmployeeIssueById(employeeIssueId);
    }

    @PostMapping("/employeeissue/register")
    public ResponseEntity<String> registerEmployeeIssue( @Valid @RequestBody EmployeeIssue employeeIssue)
    throws ResourceNotFoundException {
        return employeeIssueService.registerEmployeeIssue(employeeIssue);
    }

    @PostMapping("/employeeissue")
    public EmployeeIssue createEmployeeIssue(@Valid @RequestBody EmployeeIssue employeeIssue) {
        return employeeIssueService.createEmployeeIssue(employeeIssue);
    }

    // @PutMapping("/employeeIssue/{id}")
    // public ResponseEntity < EmployeeIssue > updateEmployeeIssue(@PathVariable(value = "id") Long employeeIssueId,
    //     @Valid @RequestBody EmployeeIssue employeeIssueDetails) throws ResourceNotFoundException {
    //     return employeeIssueService.updateEmployeeIssue(employeeIssueId, employeeIssueDetails);
    // }

    @DeleteMapping("/employeeissue/{id}")
    public Map < String, Boolean > deleteEmployeeIssue(@PathVariable(value = "id") Long employeeIssueId)
    throws ResourceNotFoundException {
        return employeeIssueService.deleteEmployeeIssue(employeeIssueId);
    }
}
