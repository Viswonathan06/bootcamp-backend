package com.example.bootcampproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bootcampproject.entity.EmployeeCardDetails;
public interface EmployeeCardRepository extends JpaRepository<EmployeeCardDetails, Long>{
    
}
