package com.example.inventoryapp.api;

import java.time.Instant;

public class LoginResponseDTO {
    private String token;
    private String tokenType;
    private Instant expiration;

    public TokenResponseDTO(String token, Instant expiresAt) {
        this.token = token;
        this.tokenType = "Bearer";
        this.expiration = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }
}
