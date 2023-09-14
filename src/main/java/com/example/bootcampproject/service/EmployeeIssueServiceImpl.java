
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

import com.example.bootcampproject.dto.EmployeeIssueDTO;
import com.example.bootcampproject.entity.Employee;
import com.example.bootcampproject.entity.EmployeeIssue;
import com.example.bootcampproject.entity.Item;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.repository.EmployeeIssueRepository;
import com.example.bootcampproject.repository.EmployeeRepository;
import com.example.bootcampproject.repository.ItemRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeIssueServiceImpl implements EmployeeIssueService{
    @Autowired
    private EmployeeIssueRepository employeeIssueRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ItemRepository itemRepository;
    

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
    public ResponseEntity<EmployeeIssue> registerEmployeeIssue(@Valid @RequestBody EmployeeIssueDTO employeeIssueDTO)
     throws ResourceNotFoundException {
        Item item = itemRepository.findById(Long.valueOf(employeeIssueDTO.getItem_id()))
            .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + employeeIssueDTO.getItem_id()));
        Employee employee = employeeRepository.findById(Long.valueOf(employeeIssueDTO.getEmployee_id()))
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeIssueDTO.getEmployee_id()));

        EmployeeIssue employeeIssue = new EmployeeIssue(employeeIssueDTO.getIssueId(),
         item,employee,employeeIssueDTO.getIssueDate(),employeeIssueDTO.getReturnDate());
        EmployeeIssue savedEmployeeIssue =  employeeIssueRepository.save(employeeIssue);
        return ResponseEntity.status(HttpStatus.OK).body(savedEmployeeIssue);
    }

}




