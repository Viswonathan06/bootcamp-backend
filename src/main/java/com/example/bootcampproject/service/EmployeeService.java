package com.example.bootcampproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

public interface EmployeeService {
    public List < Employee > getAllEmployee();
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException;
    public Employee createEmployee(@Valid @RequestBody Employee adminCredentials);
    // public ResponseEntity < Employee > updateEmployee(@PathVariable(value = "id") Long employeeId,
    //     @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException;
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException;
    public ResponseEntity<Object> verifyEmployee(@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException;
    public ResponseEntity<String> registerEmployee(@Valid @RequestBody Employee employeeDetails)
     throws ResourceNotFoundException;
}
