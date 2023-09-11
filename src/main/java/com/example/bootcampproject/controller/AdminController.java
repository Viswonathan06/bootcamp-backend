package com.example.bootcampproject.controller;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

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

import com.example.bootcampproject.entity.AdminCredentials;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.service.AdminService;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admincredentials/all")
    public List < AdminCredentials > getAllAdminCredentials() {
        return adminService.getAllAdminCredentials();
    }

    @GetMapping("/admincredentials/{id}")
    public ResponseEntity < AdminCredentials > getAdminCredentialsById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        return adminService.getAdminCredentialsById(employeeId);
    }

    @PostMapping("/admincredentials/login")
    public ResponseEntity<String> verifyAdminCredentials( @Valid @RequestBody AdminCredentials employeeDetails)
    throws ResourceNotFoundException {
        return adminService.verifyAdminCredentials(employeeDetails);
    }

    @PostMapping("/admincredentials")
    public AdminCredentials createEmployee(@Valid @RequestBody AdminCredentials adminCredentials) {
        return adminService.createEmployee(adminCredentials);
    }

    @PutMapping("/admincredentials/{id}")
    public ResponseEntity < AdminCredentials > updateEmployee(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody AdminCredentials employeeDetails) throws ResourceNotFoundException {
        return adminService.updateEmployee(employeeId, employeeDetails);
    }

    @DeleteMapping("/admincredentials/{id}")
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        return adminService.deleteEmployee(employeeId);
    }
}
