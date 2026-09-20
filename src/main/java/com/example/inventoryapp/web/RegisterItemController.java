package com.example.inventoryapp.web;


import ch.qos.logback.core.model.Model;
import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterItemController {

    @ModelAttribute
    public void addItemTypesToModel (Model model) {
        model.addAttribute("ItemType", ItemType.values());
    }

    @ModelAttribute
    public Item item(){
        return new Item();
    }

    @GetMapping
    public String registerItem() {
        return "itemRegistrationForm";
    }
}
