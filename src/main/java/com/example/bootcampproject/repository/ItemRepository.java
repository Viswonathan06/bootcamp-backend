package com.example.bootcampproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bootcampproject.entity.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    
}