package com.example.bootcampproject.controller;
import java.util.HashMap;

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
import com.example.bootcampproject.repository.AdminRepository;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admincredentials/all")
    public List < AdminCredentials > getAllAdminCredentials() {
        return adminRepository.findAll();
    }

    @GetMapping("/admincredentials/{id}")
    public ResponseEntity < AdminCredentials > getAdminCredentialsById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        AdminCredentials adminCredentials = adminRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("AdminCredentials not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(adminCredentials);
    }

    @PostMapping("/admincredentials")
    public AdminCredentials createEmployee(@Valid @RequestBody AdminCredentials adminCredentials) {
        return adminRepository.save(adminCredentials);
    }

    @PutMapping("/admincredentials/{id}")
    public ResponseEntity < AdminCredentials > updateEmployee(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody AdminCredentials employeeDetails) throws ResourceNotFoundException {
        AdminCredentials adminCredentials = adminRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("AdminCredentials not found for this id :: " + employeeId));

        adminCredentials.setEmailId(employeeDetails.getEmailId());
        adminCredentials.setUsername(employeeDetails.getUsername());
        adminCredentials.setPassword(employeeDetails.getPassword());
        final AdminCredentials updatedEmployee = adminRepository.save(adminCredentials);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/admincredentials/{id}")
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        AdminCredentials adminCredentials = adminRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("AdminCredentials not found for this id :: " + employeeId));

        adminRepository.delete(adminCredentials);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
