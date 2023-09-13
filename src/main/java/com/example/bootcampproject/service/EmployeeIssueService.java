package com.example.bootcampproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.entity.EmployeeIssue;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

public interface EmployeeIssueService {
    public List < EmployeeIssue > getAllEmployeeIssue();
    public ResponseEntity < EmployeeIssue > getEmployeeIssueById(@PathVariable(value = "id") Long employeeIssueId) throws ResourceNotFoundException;
    public EmployeeIssue createEmployeeIssue(@Valid @RequestBody EmployeeIssue employeeIssue);
    // public ResponseEntity < EmployeeIssue > updateEmployeeIssue(@PathVariable(value = "id") Long employeeIssueId,
    //     @Valid @RequestBody EmployeeIssue employeeIssue) throws ResourceNotFoundException;
    public Map < String, Boolean > deleteEmployeeIssue(@PathVariable(value = "id") Long employeeIssueId)
    throws ResourceNotFoundException;
    public ResponseEntity<String> registerEmployeeIssue(@Valid @RequestBody EmployeeIssue employeeIssue)
     throws ResourceNotFoundException;
}
