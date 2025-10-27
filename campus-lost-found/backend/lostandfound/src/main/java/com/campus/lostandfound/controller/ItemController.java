package com.campus.lostandfound.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.campus.lostandfound.model.Item;
import com.campus.lostandfound.service.ItemService;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    private static final String UPLOAD_DIR = "./uploads/";
    
    @PostMapping
    public ResponseEntity<Object> createItem(@RequestBody Item item) {
        Item savedItem = itemService.createItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }
    
    @PostMapping("/upload")
    public ResponseEntity<Object> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filepath = Paths.get(UPLOAD_DIR, filename);
            Files.write(filepath, file.getBytes());
            
            Map<String, String> response = new HashMap<>();
            response.put("filename", filename);
            response.put("path", "/uploads/" + filename);
            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (IOException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Failed to upload: " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping
    public ResponseEntity<Object> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getItemById(@PathVariable Long id) {
        Optional<Item> item = itemService.getItemById(id);
        if (item.isPresent()) {
            return new ResponseEntity<>(item.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<Object> getItemsByStatus(@PathVariable String status) {
        List<Item> items = itemService.getItemsByStatus(status);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<Object> getItemsByCategory(@PathVariable String category) {
        List<Item> items = itemService.getItemsByCategory(category);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getItemsByUser(@PathVariable Long userId) {
        List<Item> items = itemService.getItemsByUser(userId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<Object> searchItems(@RequestParam String keyword) {
        List<Item> items = itemService.searchItems(keyword);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        try {
            Item updatedItem = itemService.updateItem(id, itemDetails);
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteItem(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Item deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Item not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
}
