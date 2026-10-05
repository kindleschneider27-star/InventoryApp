package com.example.inventoryapp.services;

import com.example.inventoryapp.data.ItemRepository;
import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemType;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
    private final ItemRepository itemRepo;

    public ItemService(ItemRepository ir) {
        itemRepo = ir;
    }

    @Transactional
    public Item registerNewItem(Item item) {
        if(itemRepo.existsById(item.getId())){
            throw new IllegalArgumentException("Item already exists");
        }
        if(item.hasImage()){
            String imageName = item.getImage().getImageName();
            imageName = item.getName() + imageName.substring(imageName.lastIndexOf('.'));
            item.getImage().setImageName(imageName);
        }
        item = itemRepo.save(item);

        logger.info("Item registered : {}", item);

        return item;
    }

    public Optional<Item> getItemById(UUID id){
        return itemRepo.findById(id);
    }

    public Map<UUID, Item> getAllItems() {
        Map<UUID, Item> db = new HashMap<>();
        itemRepo.findAll().forEach(item -> db.put(item.getId(), item));
        return db;
    }

    public Map<UUID, Item> searchItems(String searchName, String searchManufacturer, ItemType filterType){
        Map<UUID, Item> db = new HashMap<>();

        boolean hasName = searchName != null && !searchName.isBlank();
        boolean hasManufacturer = searchManufacturer != null && !searchManufacturer.isBlank();
        boolean hasType = filterType != null;

        if(hasName && hasManufacturer && hasType){
            itemRepo.findByNameContainingIgnoreCaseAndTypeAndManufacturerIgnoreCase(searchName, filterType, searchManufacturer)
                    .forEach(item -> db.put(item.getId(), item));
        }

        else if(hasName && hasManufacturer){
            itemRepo.findByNameContainingIgnoreCaseAndManufacturerIgnoreCase(searchName, searchManufacturer)
                    .forEach(item -> db.put(item.getId(), item));
        }

        else if(hasName && hasType){
            itemRepo.findByNameContainingIgnoreCaseAndType(searchName, filterType)
                    .forEach(item -> db.put(item.getId(), item));
        }

        else if(hasType && hasManufacturer){
            itemRepo.findByTypeAndManufacturerIgnoreCase(filterType, searchManufacturer)
                    .forEach(item -> db.put(item.getId(), item));
        }

        else if(hasName){
            itemRepo.findByNameContainingIgnoreCase(searchName)
                    .forEach(item -> db.put(item.getId(), item));
        }

        else if(hasManufacturer){
            itemRepo.findByManufacturerIgnoreCase(searchManufacturer)
                    .forEach(item -> db.put(item.getId(), item));
        }

        else if(hasType){
            itemRepo.findByType(filterType)
                    .forEach(item -> db.put(item.getId(), item));
        }
        else{
            return getAllItems();
        }

        return db;
    }

    @Transactional
    @PreAuthorize("hasAnyRole('MNGR', 'ADMIN')")
    public boolean updateItem(UUID id, Item item) {
        if(!itemRepo.existsById(id)){
            logger.info("Item not found : {}", id);
            return false;
        }
        Item lookup = itemRepo.findById(id).get();

        if(lookup.hasImage() && !item.hasImage()){
            item.setImage(lookup.getImage());
        }

        item.setId(id);
        itemRepo.save(item);
        logger.info("Item updated : {}", item);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAnyRole('MNGR', 'ADMIN')")
    public boolean deleteItemById(UUID id) {
        if(!itemRepo.existsById(id)){
            logger.info("Item not found : {}", id);
            return false;
        }

        itemRepo.deleteById(id);
        logger.info("Item deleted : {}", id);
        return true;
    }

    @Transactional
    @PreAuthorize("hasAnyRole('MNGR','ADMIN','ASSOC')")
    public boolean updateInventory(UUID id, int newInventory) {
        if(!itemRepo.existsById(id)){
            logger.info("Item not found : {}", id);
            return false;
        }
        return itemRepo.updateInventoryById(id, newInventory) > 0;

    }

}
