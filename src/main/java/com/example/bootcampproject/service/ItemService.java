package com.example.bootcampproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.entity.Item;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;

public interface ItemService {
    public List < Item > getAllItem();
    public ResponseEntity < Item > getItemById(@PathVariable(value = "id") Long itemId) throws ResourceNotFoundException;
    public Item createItem(@Valid @RequestBody Item item);
    // public ResponseEntity < Item > updateItem(@PathVariable(value = "id") Long itemId,
    //     @Valid @RequestBody Item item) throws ResourceNotFoundException;
    public Map < String, Boolean > deleteItem(@PathVariable(value = "id") Long itemId)
    throws ResourceNotFoundException;
    public ResponseEntity<String> registerItem(@Valid @RequestBody Item item)
     throws ResourceNotFoundException;
}
