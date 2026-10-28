package com.example.inventoryapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="item")
public class ItemProperties {
    private int pageSize = 10;
    private int lowInventory =2;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getLowInventory() {
        return lowInventory;
    }
    public void setLowInventory(int lowInventory) {}
}
