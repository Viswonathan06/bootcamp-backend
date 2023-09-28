package com.example.bootcampproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.dto.EmployeeDTO;
import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

public interface EmployeeService {
    public List < EmployeeDTO > getAllEmployee();
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException;
    public Employee createEmployee(@Valid @RequestBody Employee employee);
    public ResponseEntity < List<EmployeeDTO> > updateEmployee(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException;
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException;
    public ResponseEntity<Employee> verifyEmployee(@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException;
    public ResponseEntity<Employee> registerEmployee(@Valid @RequestBody Employee employeeDetails)
     throws ResourceNotFoundException;
}
