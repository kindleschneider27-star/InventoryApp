package com.example.inventoryapp.web;

import com.example.inventoryapp.domain.ItemDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/list")
public class ItemListController {

    @Autowired
    private ItemDB itemDB;

    @ModelAttribute("pageTitle")
    public String addPageTitle()
    {
        return "List Items";
    }

    @GetMapping
    public String listItems(Model model)
    {
        model.addAttribute("itemDB", itemDB.getItems());
        return "listItems";
    }
}
