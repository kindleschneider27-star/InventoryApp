package com.example.inventoryapp.api;

import com.example.inventoryapp.domain.Image;
import com.example.inventoryapp.domain.ItemType;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ItemCreateRequestDTO {
    @NotBlank(message="Name Required")
    private String name;
    @NotBlank(message= "A manufacturer is required")
    private String manufacturer;
    private double price;
    @Positive(message= "A price is required")
    private int inventory;
    @NotNull(message = "Item type required")
    private ItemType type;
    private ImageRequestDTO image;

    public ItemCreateRequestDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public ImageRequestDTO getImage() {
        return image;
    }

    public void setImage(ImageRequestDTO image) {
        this.image = image;
    }
}
