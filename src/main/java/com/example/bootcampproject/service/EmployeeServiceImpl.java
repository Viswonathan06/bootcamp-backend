package com.example.bootcampproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.databind.JsonSerializer;
import jakarta.validation.Valid;

import org.hibernate.validator.internal.util.logging.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.dto.EmployeeDTO;
import com.example.bootcampproject.entity.AdminCredentials;
import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.entity.LoanTransaction;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.repository.EmployeeRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List < EmployeeDTO > getAllEmployee() {
        List<Employee> employees =  employeeRepository.findAll();        
        List<EmployeeDTO> empdtos =  new ArrayList();

        for( Employee emp: employees){
            List<LoanTransaction> loans = emp.getLoanTransaction();
            List<Integer> loanIDs = new ArrayList();

            for(LoanTransaction loan : loans){
                loanIDs.add(loan.getTransactionId());
            }
            EmployeeDTO temp = new EmployeeDTO(emp.getBalance(), emp.getEmployeeId(), emp.getUserName(), emp.getEmailId(), emp.getPassword(), emp.getEmployeeName(), emp.getDepartment(), emp.getDesignation(), emp.getGender(), emp.getDateOfBirth(), emp.getDateOfJoining(), loanIDs );
            empdtos.add(temp);
        }
        return empdtos;
    }

    @Override
    public ResponseEntity < List<EmployeeDTO> > updateEmployee(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setDateOfBirth(employeeDetails.getDateOfBirth());
        employee.setDateOfJoining(employeeDetails.getDateOfJoining());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setDesignation(employeeDetails.getDesignation());
        employee.setGender(employeeDetails.getGender());


        employee.setUserName(employeeDetails.getUserName());
        employee.setPassword(employeeDetails.getPassword());
         List<EmployeeDTO> empdtos =  new ArrayList();

        List<LoanTransaction> loans = employee.getLoanTransaction();
        List<Integer> loanIDs = new ArrayList();

        for(LoanTransaction loan : loans){
            loanIDs.add(loan.getTransactionId());
        }
        EmployeeDTO temp = new EmployeeDTO(employee.getBalance(), employee.getEmployeeId(), employee.getUserName(), employee.getEmailId(), employee.getPassword(), employee.getEmployeeName(), employee.getDepartment(), employee.getDesignation(), employee.getGender(), employee.getDateOfBirth(), employee.getDateOfJoining(), loanIDs );

        final Employee updatedEmployee = employeeRepository.save(employee);
        empdtos = getAllEmployee();
        return ResponseEntity.ok(empdtos);
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
        // Employee employee = employeeRepository.findById(employeeId)
        //     .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.deleteById(employeeId);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @Override
    public ResponseEntity<Employee> registerEmployee(@Valid @RequestBody Employee employeeDetails)
     throws ResourceNotFoundException {
        Employee savedEmployee =  employeeRepository.save(employeeDetails);
        System.out.println(savedEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(savedEmployee);
    }
    @Override

    public ResponseEntity<EmployeeDTO> verifyEmployee(@Valid @RequestBody Employee employeeDetails)
     throws ResourceNotFoundException {

       
        Employee employee = employeeRepository.findByUserName(employeeDetails.getUserName())
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this username :: " + employeeDetails.getUserName()));
        if(employee.getPassword().equalsIgnoreCase(employeeDetails.getPassword()) && employee.getUserName().equalsIgnoreCase(employeeDetails.getUserName())){


            Map<String, String> data = new HashMap<>();
            employee.setPassword(null);
            List<LoanTransaction> loans = employee.getLoanTransaction();
            List<Integer> loanIDs = new ArrayList();

            for(LoanTransaction loan : loans){
                loanIDs.add(loan.getTransactionId());
            }
            EmployeeDTO temp = new EmployeeDTO(employee.getBalance(), employee.getEmployeeId(), employee.getUserName(), employee.getEmailId(), employee.getPassword(), employee.getEmployeeName(), employee.getDepartment(), employee.getDesignation(), employee.getGender(), employee.getDateOfBirth(), employee.getDateOfJoining(), loanIDs );

            return ResponseEntity.status(HttpStatus.OK).body(temp);

        }else{
            final EmployeeDTO employee2 = new EmployeeDTO();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(employee2);
        }
    }
}



