package com.example.bootcampproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bootcampproject.entity.Item;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.repository.ItemRepository;

import jakarta.validation.Valid;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public List < Item > getAllItem() {
        return itemRepository.findAll();
    }
    @Override
    public ResponseEntity < Item > getItemById(@PathVariable(value = "id") Long itemId)
    throws ResourceNotFoundException {
        Item item = itemRepository.findById(itemId)
            .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));
        return ResponseEntity.ok().body(item);
    }
    @Override
    public Item createItem(@Valid @RequestBody Item item) {
        return itemRepository.save(item);
    }
    
    @Override
    public Map < String, Boolean > deleteItem(@PathVariable(value = "id") Long itemId)
    throws ResourceNotFoundException {
        Item item = itemRepository.findById(itemId)
            .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));

        itemRepository.delete(item);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @Override
    public ResponseEntity<String> registerItem(@Valid @RequestBody Item itemDetails)
     throws ResourceNotFoundException {
        Item savedItem =  itemRepository.save(itemDetails);
        return ResponseEntity.status(HttpStatus.OK).body("Item Registered!");
    }
    
}




