package com.example.inventoryapp.api;


import com.example.inventoryapp.config.ItemProperties;
import com.example.inventoryapp.data.ItemRepository;
import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemType;
import com.example.inventoryapp.domain.User;
import com.example.inventoryapp.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/items", produces = "application/json")
@CrossOrigin(origins="http:///localhost:3000")
public class ItemRestController {
    private final ItemService itemService;
    private final ItemProperties itemProperties;
    private final ItemRepository itemRepository;

    public ItemRestController(ItemService is, ItemProperties ip, ItemRepository itemRepository) {
        itemService = is;
        itemProperties = ip;
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public Page<ItemResponseDTO> getItems(@RequestParam(defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, itemProperties.getPageSize(),
        Sort.by("name").ascending().and(Sort.by("id")));

        return itemService.getAllItems(pageable).map(ItemResponseDTO::from);
    }

    @GetMapping("/search")
    public Page<ItemResponseDTO> searchItems(@RequestParam(defaultValue = "") String name,
                                             @RequestParam(required = false)ItemType type,
                                             @RequestParam(required = false)String manufacturer,
                                             @RequestParam(defaultValue = "0")  int page){
        Pageable pageable = PageRequest.of(page, itemProperties.getPageSize(),
                Sort.by("name").ascending().and(Sort.by("id")));

        return itemService.searchItems(name, type, manufacturer, pageable).map(ItemResponseDTO::from);
    }

    @GetMapping("/id")
    public ResponseEntity<ItemResponseDTO> getItem(@PathVariable UUID id){
        Optional<Item> item = itemService.getItemById(id);

        if(item.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        ItemResponseDTO response = ItemResponseDTO.from(item.get());
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ItemResponseDTO> createItem(@Valid @RequestBody ItemCreateRequestDTO request,
                                                      @AuthenticationPrincipal User user){
        Item item = ItemRequestMapper.fromCreate(request);

        Item savedItem = itemService.registerNewItem(item, user);

        ItemResponseDTO response = ItemResponseDTO.from(savedItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping(path="/{id}", consumes = "application/json")
    public ResponseEntity<ItemResponseDTO>patchItem(@PathVariable UUID id, @Valid @RequestBody ItemPatchRequestDTO request){

        Optional<Item> updatedItem = itemService.patchUpdate(id,request);
        if(updatedItem.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        ItemResponseDTO response = ItemResponseDTO.from(updatedItem.get());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id){
        boolean deleted = itemService.deleteItemById(id);

        if(!deleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
