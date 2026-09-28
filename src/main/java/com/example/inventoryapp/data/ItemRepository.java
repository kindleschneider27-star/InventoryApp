package com.example.inventoryapp.data;

import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByNameContainingIgnoreCase(String name);
    List<Item> findByManufacturerIgnoreCase(String manufacturer);
    List<Item> findByType(ItemType type);
    List<Item> findByNameContainingIgnoreCaseAndManufacturerIgnoreCase(String name, String manufacturer);
    List<Item> findByNameContainingIgnoreCaseAndType(String name, ItemType type);
    List<Item> findByTypeAndManufacturerIgnoreCase(ItemType type, String manufacturer);
    List<Item> findByNameContainingIgnoreCaseAndTypeAndManufacturerIgnoreCase(String name, ItemType type, String manufacturer);


}
