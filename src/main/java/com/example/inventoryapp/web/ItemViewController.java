package com.example.inventoryapp.web;



import com.example.inventoryapp.domain.Item;
import com.example.inventoryapp.domain.ItemType;
import com.example.inventoryapp.services.ItemService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            logger.debug("Item id not found: {} to view", id);
            return "redirect:/list";
        }

        Item current = item.get();
        model.addAttribute("current", current);
        model.addAttribute("pageTitle", current.getName());
        return "viewItem";
    }

    @GetMapping("/current/{id}/edit")
    @PreAuthorize("hasRole('MNGR') or ('ADMIN')")
    public String viewEditItem(@PathVariable UUID id, Model model) {
        Optional<Item> item = itemService.getItemById(id);
        if(item.isEmpty()){
            logger.debug("Item id not found: {} to edit", id);
            return "redirect:/list";
        }

        Item current = item.get();

        model.addAttribute("ItemType", ItemType.values());
        model.addAttribute("item", current);
        model.addAttribute("pageTitle", current.getName());

        return "itemModifyForm";
    }

    @PostMapping ("/current/{id}/edit")
    public String modifyItem (@PathVariable UUID id, @Valid Item item, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        logger.debug("Item id not found: {} to edit", id);

        if(errors.hasErrors()){
            model.addAttribute("ItemType", ItemType.values());
            model.addAttribute("pageTitle", item.getName());
            return "itemModifyForm";
        }

        if(itemService.updateItem(id, item)){
            redirectAttributes.addFlashAttribute("message", "Item updated successfully");
        }
        else{
            redirectAttributes.addFlashAttribute("message", "Item update failed");
        }
        return "redirect:/view/current/" + id;
    }

    @PostMapping("current/{id}/delete")
    public String deleteItem (@PathVariable UUID id, RedirectAttributes redirectAttributes) {

        if(itemService.deleteItemById(id)){
            redirectAttributes.addFlashAttribute("message", "Item deleted successfully");
        }
        else {
            redirectAttributes.addFlashAttribute("message", "Item delete failed");
        }
        return "redirect:/list";
    }

    @PostMapping("/current/{id}")
    public String editInventory (@PathVariable UUID id, int newInventory, RedirectAttributes redirectAttributes) {

        if(itemService.updateInventory(id, newInventory)){
            redirectAttributes.addFlashAttribute("message", "Inventory updated successfully");
        }
        else {
            redirectAttributes.addFlashAttribute("message", "Inventory update failed");
        }
        return "redirect:/view/current/" + id;
    }

}
