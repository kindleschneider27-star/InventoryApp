package com.example.inventoryapp.data;

import com.example.inventoryapp.domain.ApiToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.UUID;

/**
 * TODO
 * Make sure you set up the package statement at the top
 *
 * Import the ApiToken file.
 *
 * Delete this comment when done.
 *
 */

public interface TokenRepository extends JpaRepository<ApiToken, UUID> {
    boolean existsByIdAndRevokedAtIsNullAndExpiresAtAfter(UUID id, Instant now);
}
