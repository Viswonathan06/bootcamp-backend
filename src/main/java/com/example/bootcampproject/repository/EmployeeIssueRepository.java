package com.example.bootcampproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bootcampproject.entity.EmployeeIssue;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeIssueRepository extends JpaRepository<EmployeeIssue, Long>{
    
}
