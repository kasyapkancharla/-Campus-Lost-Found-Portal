package com.campus.lostandfound.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.lostandfound.model.Claim;
import com.campus.lostandfound.service.ClaimService;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = "http://localhost:3000")
public class ClaimController {
    
    @Autowired
    private ClaimService claimService;
    
    @PostMapping
    public ResponseEntity<Object> createClaim(@RequestBody Claim claim) {
        Claim savedClaim = claimService.createClaim(claim);
        return new ResponseEntity<>(savedClaim, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<Object> getAllClaims() {
        List<Claim> claims = claimService.getAllClaims();
        return new ResponseEntity<>(claims, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getClaimById(@PathVariable Long id) {
        Optional<Claim> claim = claimService.getClaimById(id);
        if (claim.isPresent()) {
            return new ResponseEntity<>(claim.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/item/{itemId}")
    public ResponseEntity<Object> getClaimsByItem(@PathVariable Long itemId) {
        List<Claim> claims = claimService.getClaimsByItem(itemId);
        return new ResponseEntity<>(claims, HttpStatus.OK);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getClaimsByUser(@PathVariable Long userId) {
        List<Claim> claims = claimService.getClaimsByUser(userId);
        return new ResponseEntity<>(claims, HttpStatus.OK);
    }
    
    @GetMapping("/pending")
    public ResponseEntity<Object> getPendingClaims() {
        List<Claim> claims = claimService.getPendingClaims();
        return new ResponseEntity<>(claims, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Object> updateClaimStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        try {
            String status = statusUpdate.get("status");
            String comments = statusUpdate.getOrDefault("comments", "");
            Claim updatedClaim = claimService.updateClaimStatus(id, status, comments);
            return new ResponseEntity<>(updatedClaim, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
