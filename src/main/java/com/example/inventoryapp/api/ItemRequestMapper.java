package com.example.inventoryapp.api;

import com.example.inventoryapp.domain.Image;
import com.example.inventoryapp.domain.Item;

import java.util.Base64;

public class ItemRequestMapper {

    private ItemRequestMapper() {}

    public static Item fromCreate(ItemCreateRequestDTO request) {
        Item item = new Item();

        item.setName(request.getName());
        item.setManufacturer(request.getManufacturer());
        item.setPrice(request.getPrice());
        item.setInventory(request.getInventory());
        item.setType(request.getType());
        item.setImage(fromImageRequest(request.getImage()));

        return item;
    }

    public static Image fromImageRequest(ImageRequestDTO request) {
        if(request == null|| request.getContents() == null || request.getContents().isBlank()) {
            return new Image();
        }
        try{
            byte[] contents = Base64.getDecoder().decode(request.getContents());
            return new Image(request.getImageName(), request.getEncoding(),  contents);
        }

        catch(IllegalArgumentException ex){
            return new Image();
        }

    }
}
