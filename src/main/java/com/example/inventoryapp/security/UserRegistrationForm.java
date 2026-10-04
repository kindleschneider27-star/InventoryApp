package com.example.inventoryapp.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationForm {
    @NotBlank(message = "Must enter a username")
    @Size(min=5, max=50, message = "username can be between 5 and 50 characters")
    private String username;
    @NotBlank(message = "Must enter a password")
    @Size(min=8, message="must be at least 8 characters")
    private String password;
    @NotBlank(message = "Must renter a password")
    private String confirmPassword;
    @NotBlank(message = "Must enter a full name")
    private String fullName;

    public UserRegistrationForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
