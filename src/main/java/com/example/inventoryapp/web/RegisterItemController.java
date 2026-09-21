package com.example.inventoryapp.web;


import com.example.inventoryapp.domain.ItemDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterItemController {
    @Autowired
    private ItemDB itemDB;

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
    public String processItemRegister (Item item){

        if(item.hasImage()){
            String imageName = item.getImage().getName();
            imageName = item.getName() + imageName.substring(imageName.lastIndexOf('.'));
            item.getImage().setName(imageName);
        }
        itemDB.addItem(item);

        return "redirect:/view/current/" + item.getId();
    }
}
