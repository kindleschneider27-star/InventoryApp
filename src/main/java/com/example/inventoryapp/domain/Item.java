package com.example.inventoryapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

@Entity
public class Item {

    @Id
    @NotBlank(message= "An id is required")
    private UUID id;
    @NotBlank(message= "A name is required")
    private String name;
    @NotBlank(message= "A manufacturer is required")
    private String manufacturer;
    @Positive(message= "A price is required")
    private double price;
    private int inventory;

    @Enumerated(EnumType.STRING)
    private ItemType type;
    @Embedded
    private Image image;

    public Item(UUID id, String name, String manufacturer, double price, int inventory, ItemType type, Image image) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.inventory = inventory;
        this.type = type;
        this.image = image;
    }

    public Item() {
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

    public void setType(ItemType itemType) {
        this.type = itemType;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean hasImage() {
        return image != null && image.getContents().length >0;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", type=" + type +
                ", image=" + image +
                '}';
    }
}
