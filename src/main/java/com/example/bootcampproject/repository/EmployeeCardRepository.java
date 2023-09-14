package com.example.bootcampproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bootcampproject.entity.EmployeeCardDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCardDetails, Long>{
    
}
