package com.example.inventoryapp.domain;

public enum ItemType {
    CONSUMABLE,
    APPAREL,
    ACCESSORY,
    BOOK,
    SCHOOL;

    @Override
    public String toString() {
        return switch(this.ordinal()){
            case 0 -> "Food & Drink";
            case 1 -> "Apparel";
            case 2 -> "Accessories";
            case 3 -> "Book";
            default -> "School Materials";
        };
    }
}
