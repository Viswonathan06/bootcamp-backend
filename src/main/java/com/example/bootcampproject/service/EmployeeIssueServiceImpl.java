
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

import com.example.bootcampproject.entity.EmployeeIssue;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.repository.EmployeeIssueRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeIssueServiceImpl implements EmployeeIssueService{
    @Autowired
    private EmployeeIssueRepository employeeIssueRepository;
    public EmployeeIssueServiceImpl(EmployeeIssueRepository employeeIssueRepository){
        this.employeeIssueRepository = employeeIssueRepository;
    }

    @Override
    public List < EmployeeIssue > getAllEmployeeIssue() {
        return employeeIssueRepository.findAll();
    }
    @Override
    public ResponseEntity < EmployeeIssue > getEmployeeIssueById(@PathVariable(value = "id") Long employeeIssueId)
    throws ResourceNotFoundException {
        EmployeeIssue employeeIssue = employeeIssueRepository.findById(employeeIssueId)
            .orElseThrow(() -> new ResourceNotFoundException("EmployeeIssue not found for this id :: " + employeeIssueId));
        return ResponseEntity.ok().body(employeeIssue);
    }
    @Override
    public EmployeeIssue createEmployeeIssue(@Valid @RequestBody EmployeeIssue employeeIssue) {
        return employeeIssueRepository.save(employeeIssue);
    }
    
    @Override
    public Map < String, Boolean > deleteEmployeeIssue(@PathVariable(value = "id") Long employeeIssueId)
    throws ResourceNotFoundException {
        EmployeeIssue employeeIssue = employeeIssueRepository.findById(employeeIssueId)
            .orElseThrow(() -> new ResourceNotFoundException("EmployeeIssue not found for this id :: " + employeeIssueId));

        employeeIssueRepository.delete(employeeIssue);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @Override
    public ResponseEntity<String> registerEmployeeIssue(@Valid @RequestBody EmployeeIssue employeeIssueDetails)
     throws ResourceNotFoundException {
        EmployeeIssue savedEmployeeIssue =  employeeIssueRepository.save(employeeIssueDetails);
        return ResponseEntity.status(HttpStatus.OK).body("EmployeeIssue Registered!");
    }

}




