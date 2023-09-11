package com.example.bootcampproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bootcampproject.entity.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
}