package com.example.bootcampproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bootcampproject.dto.EmployeeDTO;
import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.service.EmployeeService;

import jakarta.validation.Valid;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/all")
    public List < EmployeeDTO > getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/employee/login")
    public ResponseEntity<Object> verifyEmployee( @Valid @RequestBody Employee employee)
    throws ResourceNotFoundException {
        return employeeService.verifyEmployee(employee);
    }

    @PostMapping("/employee/register")
    public ResponseEntity<Employee> registerEmployee( @Valid @RequestBody Employee employee)
    throws ResourceNotFoundException {
        return employeeService.registerEmployee(employee);
    }

    @PostMapping("/employee")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity < List<EmployeeDTO> > updateEmployee(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        return employeeService.updateEmployee(employeeId, employeeDetails);
    }

    @DeleteMapping("/employee/{id}")
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        return employeeService.deleteEmployee(employeeId);
    }
}
