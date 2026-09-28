package com.example.inventoryapp.web;



import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemDB;
import com.example.inventoryapp.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/view")
public class ItemViewController {
    private static final Logger logger = LoggerFactory.getLogger(ItemViewController.class);

    private final ItemService itemService;

    public ItemViewController(ItemService is) {
        itemService = is;
    }


    @GetMapping("/current/{id}")
    public String viewItem(@PathVariable("id") UUID id, Model model) {

        Optional<Item> item = itemService.getItemById(id);
        if(item.isEmpty()){
            logger.debug("Item id not found: {}", id);
            return "redirect:/list";
        }

        Item current = item.get();
        model.addAttribute("current", current);
        model.addAttribute("pageTitle", current.getName());
        return "viewItem";
    }
}
