package com.example.bootcampproject.service;

import com.example.bootcampproject.repository.AdminRepository;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

import com.example.bootcampproject.controller.AdminController;
import com.example.bootcampproject.entity.AdminCredentials;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

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
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Override
    public List < AdminCredentials > getAllAdminCredentials() {
        List<AdminCredentials> lst =  adminRepository.findAll();
        for(AdminCredentials ac: lst){
            ac.setPassword(null);
        }
        return lst;
    }
    @Override
    public ResponseEntity < AdminCredentials > getAdminCredentialsById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        AdminCredentials adminCredentials = adminRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("AdminCredentials not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(adminCredentials);
    }
    @Override
    public AdminCredentials createAdminCredentials(@Valid @RequestBody AdminCredentials adminCredentials) {
        return adminRepository.save(adminCredentials);
    }
    @Override
    public ResponseEntity < AdminCredentials > updateAdmin(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody AdminCredentials employeeDetails) throws ResourceNotFoundException {
        AdminCredentials adminCredentials = adminRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("AdminCredentials not found for this id :: " + employeeId));

        adminCredentials.setEmailId(employeeDetails.getEmailId());
        adminCredentials.setUserName(employeeDetails.getUserName());
        adminCredentials.setPassword(employeeDetails.getPassword());
        final AdminCredentials updatedEmployee = adminRepository.save(adminCredentials);
        return ResponseEntity.ok(updatedEmployee);
    }
    @Override
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        AdminCredentials adminCredentials = adminRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("AdminCredentials not found for this id :: " + employeeId));

        adminRepository.delete(adminCredentials);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @Override
    public ResponseEntity<AdminCredentials> registerAdminCredentials(@Valid @RequestBody AdminCredentials employeeDetails)
     throws ResourceNotFoundException {
        AdminCredentials savedAdmin =  adminRepository.save(employeeDetails);
        savedAdmin.setPassword(null);
        return ResponseEntity.status(HttpStatus.OK).body(savedAdmin);
    }
    @Override

    public ResponseEntity<Object> verifyAdminCredentials(@Valid @RequestBody AdminCredentials employeeDetails)
     throws ResourceNotFoundException {

       
        AdminCredentials adminCredentials = adminRepository.findByUserName(employeeDetails.getUserName())
            .orElseThrow(() -> new ResourceNotFoundException("AdminCredentials not found for this username :: " + employeeDetails.getUserName()));
        if(adminCredentials.getPassword().equalsIgnoreCase(employeeDetails.getPassword()) && adminCredentials.getUserName().equalsIgnoreCase(employeeDetails.getUserName())){
            Map<String, String> data = new HashMap<>();
            data.put("role","ADMIN");
            return new ResponseEntity<>(data, HttpStatus.OK);
        
        }else{

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Admin Unauthorized");
        }
    }
}


