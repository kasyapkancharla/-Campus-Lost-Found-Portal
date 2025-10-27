package com.campus.lostandfound.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campus.lostandfound.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByStatus(String status);
    List<Item> findByCategory(String category);
    List<Item> findByUserId(Long userId);
    List<Item> findByStatusAndActiveTrue(String status);
    List<Item> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}
