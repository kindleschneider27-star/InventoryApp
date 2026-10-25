package com.example.inventoryapp.api;

import com.example.inventoryapp.domain.User;

import java.util.UUID;

public class UserResponseDTO {
    private UUID id;
    private String username;
    private String fullname;

    public static UserResponseDTO from(User user) {
        UserResponseDTO response =
                new UserResponseDTO();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFullname(user.getFullName());

        return response;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}