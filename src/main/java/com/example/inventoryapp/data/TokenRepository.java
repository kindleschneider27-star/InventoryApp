package com.example.inventoryapp.data;

import com.example.inventoryapp.domain.ApiToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.UUID;


public interface TokenRepository extends JpaRepository<ApiToken, UUID> {
    boolean existsByIdAndRevokedAtIsNullAndExpiresAtAfter(UUID id, Instant now);
}
