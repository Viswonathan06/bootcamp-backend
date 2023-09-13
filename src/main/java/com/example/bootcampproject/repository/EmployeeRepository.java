package com.example.bootcampproject.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bootcampproject.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
        Optional<Employee> findByUserName(String username);

}