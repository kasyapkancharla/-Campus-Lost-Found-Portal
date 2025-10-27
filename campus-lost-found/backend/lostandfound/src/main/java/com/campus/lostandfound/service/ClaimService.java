package com.campus.lostandfound.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.lostandfound.model.Claim;
import com.campus.lostandfound.repository.ClaimRepository;

@Service
public class ClaimService {
    
    @Autowired
    private ClaimRepository claimRepository;
    
    public Claim createClaim(Claim claim) {
        claim.setCreatedAt(LocalDateTime.now());
        claim.setStatus("PENDING");
        return claimRepository.save(claim);
    }
    
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
    
    public Optional<Claim> getClaimById(Long id) {
        return claimRepository.findById(id);
    }
    
    public List<Claim> getClaimsByItem(Long itemId) {
        return claimRepository.findByItemId(itemId);
    }
    
    public List<Claim> getClaimsByUser(Long userId) {
        return claimRepository.findByUserId(userId);
    }
    
    public List<Claim> getPendingClaims() {
        return claimRepository.findByStatus("PENDING");
    }
    
    public Claim updateClaimStatus(Long id, String status, String adminComments) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
        claim.setStatus(status);
        claim.setAdminComments(adminComments);
        claim.setUpdatedAt(LocalDateTime.now());
        return claimRepository.save(claim);
    }
}
