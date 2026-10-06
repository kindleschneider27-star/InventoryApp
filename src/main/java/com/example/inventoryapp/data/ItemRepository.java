package com.example.inventoryapp.data;

import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    Page<Item> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Item> findByManufacturerIgnoreCase(String manufacturer, Pageable pageable);
    Page<Item> findByType(ItemType type, Pageable pageable);
    Page<Item> findByNameContainingIgnoreCaseAndManufacturerIgnoreCase(String name, String manufacturer, Pageable pageable);
    Page<Item> findByNameContainingIgnoreCaseAndType(String name, ItemType type, Pageable pageable);
    Page<Item> findByTypeAndManufacturerIgnoreCase(ItemType type, String manufacturer, Pageable pageable);
    Page<Item> findByNameContainingIgnoreCaseAndTypeAndManufacturerIgnoreCase(String name, ItemType type, String manufacturer, Pageable pageable);

    @Modifying
    @Query("update Item i set i.inventory = :inventory where i.id= :id") //got this line from AI dont know how i was supposed to come up with this
    int updateInventoryById(@Param("id")UUID id, @Param("inventory") int inventory);


}
