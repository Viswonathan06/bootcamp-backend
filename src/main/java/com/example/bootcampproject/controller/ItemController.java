package com.example.bootcampproject.controller;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bootcampproject.entity.Item;
import com.example.bootcampproject.exceptions.ResourceNotFoundException;
import com.example.bootcampproject.service.ItemService;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/item/all")
    public List < Item > getAllItem() {
        return itemService.getAllItem();
    }

    @GetMapping("/item/{id}")
    public ResponseEntity < Item > getItemById(@PathVariable(value = "id") Long itemId)
    throws ResourceNotFoundException {
        return itemService.getItemById(itemId);
    }


    @PostMapping("/item/create")
    public ResponseEntity<String> registerItem( @Valid @RequestBody Item item)
    throws ResourceNotFoundException {
        return itemService.registerItem(item);
    }

    @PostMapping("/item")
    public Item createItem(@Valid @RequestBody Item item) {
        return itemService.createItem(item);
    }

    // @PutMapping("/item/{id}")
    // public ResponseEntity < Item > updateItem(@PathVariable(value = "id") Long itemId,
    //     @Valid @RequestBody Item itemDetails) throws ResourceNotFoundException {
    //     return itemService.updateItem(itemId, itemDetails);
    // }

    @DeleteMapping("/item/{id}")
    public Map < String, Boolean > deleteItem(@PathVariable(value = "id") Long itemId)
    throws ResourceNotFoundException {
        return itemService.deleteItem(itemId);
    }
}
