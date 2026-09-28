package com.example.inventoryapp.web;

import com.example.inventoryapp.domain.ItemDB;
import com.example.inventoryapp.domain.ItemType;
import com.example.inventoryapp.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @ModelAttribute
    public void addItemTypesToModel (Model model) {
        model.addAttribute("ItemType", ItemType.values());
    }


    @GetMapping
    public String listItems(@RequestParam(defaultValue = "") String searchName,
                            @RequestParam(defaultValue = "")String  searchManufacturer,
                            @RequestParam(required = false) ItemType filterType,
                            Model model)
    {
        model.addAttribute("itemDB", itemService.searchItems(searchName, searchManufacturer, filterType));

        model.addAttribute("searchName", searchName);
        model.addAttribute("searchManufacturer", searchManufacturer);
        model.addAttribute("filterType", filterType);
        return "listItems";
    }
}
