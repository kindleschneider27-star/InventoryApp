package com.example.inventoryapp.services;

import com.example.inventoryapp.api.ItemPatchRequestDTO;
import com.example.inventoryapp.api.ItemRequestMapper;
import com.example.inventoryapp.data.ItemRepository;
import com.example.inventoryapp.data.UserRepository;
import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemType;
import com.example.inventoryapp.domain.User;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
    private final ItemRepository itemRepo;
    private final UserRepository userRepo;

    public ItemService(ItemRepository ir, UserRepository ur) {
        itemRepo = ir;
        userRepo = ur;
    }

    @Transactional
    @PreAuthorize("hasAnyRole('MNGR','ADMIN')")
    public Item registerNewItem(Item item, String username) {
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

    public Page<Item> getAllItems(Pageable pageable) {
        return itemRepo.findAll(pageable);

    }

    public Page<Item> searchItems(String searchName, String searchManufacturer, ItemType filterType, Pageable pageable){

        boolean hasName = searchName != null && !searchName.isBlank();
        boolean hasManufacturer = searchManufacturer != null && !searchManufacturer.isBlank();
        boolean hasType = filterType != null;

        if(hasName && hasManufacturer && hasType){
            return itemRepo.findByNameContainingIgnoreCaseAndTypeAndManufacturerIgnoreCase(searchName, filterType, searchManufacturer, pageable);
        }

        else if(hasName && hasManufacturer){
            return itemRepo.findByNameContainingIgnoreCaseAndManufacturerIgnoreCase(searchName, searchManufacturer, pageable);
        }

        else if(hasName && hasType){
            return itemRepo.findByNameContainingIgnoreCaseAndType(searchName, filterType, pageable);

        }

        else if(hasType && hasManufacturer){
            return itemRepo.findByTypeAndManufacturerIgnoreCase(filterType, searchManufacturer, pageable);

        }

        else if(hasName){
            return itemRepo.findByNameContainingIgnoreCase(searchName, pageable);

        }

        else if(hasManufacturer){
            return itemRepo.findByManufacturerIgnoreCase(searchManufacturer, pageable);

        }

        else if(hasType){
            return itemRepo.findByType(filterType, pageable);

        }
        else{
            return getAllItems(pageable);
        }

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

    @Transactional
    @PreAuthorize("hasAnyRole('MNGR', 'ADMIN')")
    public Optional<Item> patchUpdate(UUID id, ItemPatchRequestDTO request) {
        Optional<Item> lookupItem = itemRepo.findById(id);

        if(lookupItem.isEmpty()){
            return Optional.empty();
        }

        Item item = lookupItem.get();

        if(request.getName() != null){
            item.setName(request.getName());
        }

        if(request.getManufacturer() != null){
            item.setManufacturer(request.getManufacturer());
        }

        if(request.getPrice() != null) {
            item.setPrice(request.getPrice());
        }

        if(request.getInventory() != null) {
            item.setInventory(request.getInventory());
        }

        if(request.getType() != null){
            item.setType(request.getType());
        }

        if(request.getImage() != null){
            item.setImage(ItemRequestMapper.fromImageRequest(request.getImage()));
        }

        Item updatedItem = itemRepo.save(item);

        return Optional.of(updatedItem);
    }

}
