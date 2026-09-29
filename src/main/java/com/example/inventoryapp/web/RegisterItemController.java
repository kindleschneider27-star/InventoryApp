package com.example.inventoryapp.web;



import com.example.inventoryapp.services.ItemService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/register")
public class RegisterItemController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterItemController.class);

    private final ItemService itemService;

    public RegisterItemController(ItemService is) {
        itemService = is;
    }

    @ModelAttribute("pageTitle")
    public String addPageTitle(){
        return "Register Item";
    }

    @ModelAttribute
    public void addItemTypesToModel (Model model) {
        model.addAttribute("ItemType", ItemType.values());
    }

    @ModelAttribute
    public Item item(){
        return new Item();
    }

    @GetMapping
    public String registerForm() {
        return "itemRegistrationForm";
    }

    @PostMapping
    public String processItemRegister (@Valid Item item, Errors errors) {
        logger.debug("Item registered : {}", item);

        if (errors.hasErrors()) {
            return "itemRegistrationForm";
        }

        try{
            Item addedItem = itemService.registerNewItem(item);
            return "redirect:/view/current/" + addedItem.getId();
        }
        catch (IllegalArgumentException e){
            errors.rejectValue("id", "duplicate", "Item with this Id already exists"); //got this from AI since i cant use notblank with UUID
            return "itemRegistrationForm";
        }


    }

}
