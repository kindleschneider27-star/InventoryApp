package com.example.inventoryapp.web;



import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/view")
public class ItemViewController {

    @Autowired
    private ItemDB itemDB;

    @GetMapping("/current/{id}")
    public String viewItem(@PathVariable("id") UUID id, Model model) {

        Item current = itemDB.getItems().get(id);
        model.addAttribute("current", current);
        return "viewItem";
    }
}
