package com.example.bootcampproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.entity.AdminCredentials;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.repository.AdminRepository;

import jakarta.validation.Valid;

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
    public ResponseEntity < AdminCredentials > getAdminCredentialsById(@PathVariable(value = "id") Long adminId)
    throws ResourceNotFoundException {
        AdminCredentials adminCredentials = adminRepository.findById(adminId)
            .orElseThrow(() -> new ResourceNotFoundException("AdminCredentials not found for this id :: " + adminId));
        return ResponseEntity.ok().body(adminCredentials);
    }
    @Override
    public AdminCredentials createAdminCredentials(@Valid @RequestBody AdminCredentials adminCredentials) {
        return adminRepository.save(adminCredentials);
    }
    @Override
    public ResponseEntity < AdminCredentials > updateAdmin(@PathVariable(value = "id") Long adminId,
        @Valid @RequestBody AdminCredentials adminDetails) throws ResourceNotFoundException {
        AdminCredentials adminCredentials = adminRepository.findById(adminId)
            .orElseThrow(() -> new ResourceNotFoundException("AdminCredentials not found for this id :: " + adminId));

        adminCredentials.setEmailId(adminDetails.getEmailId());
        adminCredentials.setUserName(adminDetails.getUserName());
        adminCredentials.setPassword(adminDetails.getPassword());
        final AdminCredentials updatedEmployee = adminRepository.save(adminCredentials);
        return ResponseEntity.ok(updatedEmployee);
    }
    @Override
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long adminId)
    throws ResourceNotFoundException {
        
        adminRepository.deleteById(adminId);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @Override
    public ResponseEntity<AdminCredentials> registerAdminCredentials(@Valid @RequestBody AdminCredentials adminDetails)
     throws ResourceNotFoundException {
        AdminCredentials savedAdmin =  adminRepository.save(adminDetails);
        
        return ResponseEntity.status(HttpStatus.OK).body(savedAdmin);
    }
    @Override

    public ResponseEntity<AdminCredentials> verifyAdminCredentials(@Valid @RequestBody AdminCredentials adminDetails)
     throws ResourceNotFoundException {

       
        AdminCredentials adminCredentials = adminRepository.findByUserName(adminDetails.getUserName())
            .orElseThrow(() -> new ResourceNotFoundException("AdminCredentials not found for this username :: " + adminDetails.getUserName()));
        if(adminCredentials.getPassword().equalsIgnoreCase(adminDetails.getPassword()) && adminCredentials.getUserName().equalsIgnoreCase(adminDetails.getUserName())){
            Map<String, String> data = new HashMap<>();
            adminCredentials.setRole("ADMIN");
            return ResponseEntity.status(HttpStatus.OK).body(adminCredentials);
        
        }else{
            adminCredentials.setRole("UNAUTHORIZED");
            return ResponseEntity.status(HttpStatus.OK).body(adminCredentials);
        }
    }
}


