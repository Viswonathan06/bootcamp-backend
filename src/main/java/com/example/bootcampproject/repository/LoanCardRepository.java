package com.example.bootcampproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bootcampproject.entity.LoanCard;

@Repository
public interface LoanCardRepository extends JpaRepository<LoanCard, Long>{

}
