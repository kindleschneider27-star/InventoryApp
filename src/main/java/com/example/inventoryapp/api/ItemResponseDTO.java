package com.example.inventoryapp.api;

import com.example.inventoryapp.domain.Image;
import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemType;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class ItemResponseDTO {
    private UUID id;
    private String name;
    private String manufacturer;
    private double price;
    private int inventory;
    private ItemType type;
    private ImageResponseDTO image;

    public ItemResponseDTO() {}

    public static ItemResponseDTO create(Item item) {
        ItemResponseDTO response = new ItemResponseDTO();

        response.setId(item.getId());
        response.setName(item.getName());
        response.setManufacturer(item.getManufacturer());
        response.setPrice(item.getPrice());
        response.setInventory(item.getInventory());
        response.setType(item.getType());
        response.setImage(ImageResponseDTO.from(item.getImage()));

        return response;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public ImageResponseDTO getImage() {
        return image;
    }

    public void setImage(ImageResponseDTO image) {
        this.image = image;
    }
}
