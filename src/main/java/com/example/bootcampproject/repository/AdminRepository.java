package com.example.bootcampproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bootcampproject.entity.AdminCredentials;

@Repository
public interface AdminRepository extends JpaRepository<AdminCredentials, Long>{

}