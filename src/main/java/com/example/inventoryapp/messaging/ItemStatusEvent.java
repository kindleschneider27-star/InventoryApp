package com.example.inventoryapp.messaging;

import java.util.UUID;

public class ItemStatusEvent {
    private UUID eventId;
    private UUID itemId;
    private ItemStatus status;

    private String itemName;
    private String itemManufacturer;
    private int itemInventory;


    public ItemStatusEvent() {
    }

//constructor?? from what though no lostpet

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemManufacturer() {
        return itemManufacturer;
    }

    public void setItemManufacturer(String itemManufacturer) {
        this.itemManufacturer = itemManufacturer;
    }

    public int getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(int itemInventory) {
        this.itemInventory = itemInventory;
    }
}
