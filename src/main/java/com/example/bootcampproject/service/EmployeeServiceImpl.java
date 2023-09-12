package com.example.bootcampproject.service;

import com.example.bootcampproject.repository.EmployeeRepository;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

import com.example.bootcampproject.controller.EmployeeController;
import com.example.bootcampproject.entity.Employee;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonSerializer;
import jakarta.validation.Valid;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List < Employee > getAllEmployee() {
        return employeeRepository.findAll();
    }
    @Override
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
    @Override
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    
    @Override
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @Override
    public ResponseEntity<String> registerEmployee(@Valid @RequestBody Employee employeeDetails)
     throws ResourceNotFoundException {
        Employee savedEmployee =  employeeRepository.save(employeeDetails);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Registered!");
    }
    @Override
    public ResponseEntity<Object> verifyEmployee(@Valid @RequestBody Employee employeeDetails)
     throws ResourceNotFoundException {

       
        Employee employee = employeeRepository.findByUserName(employeeDetails.getUserName())
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this username :: " + employeeDetails.getUserName()));
        if(employee.getPassword().equalsIgnoreCase(employeeDetails.getPassword()) && employee.getUserName().equalsIgnoreCase(employeeDetails.getUserName())){

            Map<String, String> data = new HashMap<>();
            data.put("role", "NULL");
            return new ResponseEntity<>(data, HttpStatus.OK);


        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Employee Unauthorized");
        }
    }
}



