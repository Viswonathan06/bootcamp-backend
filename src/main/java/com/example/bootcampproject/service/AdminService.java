package com.example.bootcampproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.entity.AdminCredentials;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

public interface AdminService {
    public List < AdminCredentials > getAllAdminCredentials();
    public ResponseEntity < AdminCredentials > getAdminCredentialsById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException;
    public AdminCredentials createAdminCredentials(@Valid @RequestBody AdminCredentials adminCredentials);
    public ResponseEntity < AdminCredentials > updateAdmin(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody AdminCredentials employeeDetails) throws ResourceNotFoundException;
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException;
    public ResponseEntity<Object> verifyAdminCredentials(@Valid @RequestBody AdminCredentials employeeDetails) throws ResourceNotFoundException;
    public ResponseEntity<AdminCredentials> registerAdminCredentials(@Valid @RequestBody AdminCredentials employeeDetails)
     throws ResourceNotFoundException;
}
