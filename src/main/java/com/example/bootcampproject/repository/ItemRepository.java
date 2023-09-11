package com.example.bootcampproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bootcampproject.entity.Item;
public interface ItemRepository extends JpaRepository<Item, Long>{
    
}