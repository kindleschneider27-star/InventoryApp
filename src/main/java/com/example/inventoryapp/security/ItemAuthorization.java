package com.example.inventoryapp.security;

import com.example.inventoryapp.data.ItemRepository;
import org.springframework.stereotype.Component;

@Component
public class ItemAuthorization {
    private final ItemRepository itemRepository;

    public ItemAuthorization(ItemRepository ir) {
        itemRepository = ir;
    }

}
