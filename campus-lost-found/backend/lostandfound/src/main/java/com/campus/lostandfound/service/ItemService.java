package com.campus.lostandfound.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.lostandfound.model.Item;
import com.campus.lostandfound.repository.ItemRepository;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemRepository;
    
    public Item createItem(Item item) {
        item.setCreatedAt(LocalDateTime.now());
        return itemRepository.save(item);
    }
    
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }
    
    public List<Item> getItemsByStatus(String status) {
        return itemRepository.findByStatusAndActiveTrue(status);
    }
    
    public List<Item> getItemsByCategory(String category) {
        return itemRepository.findByCategory(category);
    }
    
    public List<Item> getItemsByUser(Long userId) {
        return itemRepository.findByUserId(userId);
    }
    
    public List<Item> searchItems(String keyword) {
        return itemRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }
    
    public Item updateItem(Long id, Item itemDetails) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setTitle(itemDetails.getTitle());
        item.setDescription(itemDetails.getDescription());
        item.setCategory(itemDetails.getCategory());
        item.setStatus(itemDetails.getStatus());
        item.setLocation(itemDetails.getLocation());
        item.setUpdatedAt(LocalDateTime.now());
        return itemRepository.save(item);
    }
    
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setActive(false);
        itemRepository.save(item);
    }
}
