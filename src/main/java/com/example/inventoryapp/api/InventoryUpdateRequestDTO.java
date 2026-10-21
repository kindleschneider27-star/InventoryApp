package com.example.inventoryapp.api;

import jakarta.validation.constraints.NotNull;

public class InventoryUpdateRequestDTO {
    @NotNull(message = "Inventory value required")
    private Integer inventory;

    public InventoryUpdateRequestDTO() {
    }

    public InventoryUpdateRequestDTO(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
