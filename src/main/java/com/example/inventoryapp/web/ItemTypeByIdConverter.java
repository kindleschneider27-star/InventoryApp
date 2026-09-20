package com.example.inventoryapp.web;


import com.example.inventoryapp.domain.ItemType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ItemTypeByIdConverter implements Converter<String, ItemType> {

    @Override
    public ItemType convert(String source) {
        if(source.equals("Food & Drink")){
            return ItemType.CONSUMABLE;
        }
        if(source.equals("Accessories")){
            return ItemType.ACCESSORY;
        }
        if(source.equals("School Materials")){
            return ItemType.SCHOOL;
        }
        return ItemType.valueOf(source.toUpperCase());
    }

}
