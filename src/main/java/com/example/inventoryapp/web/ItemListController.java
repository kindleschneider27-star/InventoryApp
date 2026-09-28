package com.example.inventoryapp.web;

import com.example.inventoryapp.domain.ItemDB;
import com.example.inventoryapp.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
public class ItemListController {
    private static final Logger logger = LoggerFactory.getLogger(ItemListController.class);

    private final ItemService itemService;

    public ItemListController(ItemService is) {
        itemService = is;
    }




    @ModelAttribute("pageTitle")
    public String addPageTitle()
    {
        return "List Items";
    }

    @GetMapping
    public String listItems(Model model)
    {
        model.addAttribute("itemDB", itemService.getAllItems());
        return "listItems";
    }
}
