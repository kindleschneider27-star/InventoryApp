package com.example.inventoryapp.data;

import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Modifying
    @Query("update Item i set i.inventory = :inventory where i.id= :id") //got this line from AI dont know how i was supposed to come up with this
    int updateInventoryById(@Param("id")UUID id, @Param("inventory") int inventory);


}
