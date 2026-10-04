package com.example.inventoryapp.security;

import com.example.inventoryapp.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserRegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);
    private final UserService userService;

    public UserRegistrationController(UserService us) {
        userService = us;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        model.addAttribute("pageTitle", "User Registration");
        return "userRegistrationForm";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid UserRegistrationForm userRegistrationForm, Errors errors, Model model) {
        if(!userRegistrationForm.getPassword().equals(userRegistrationForm.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Password.mismatch", "Passwords do not match");
        }

        if(errors.hasErrors()) {
            return "userRegistrationForm";
        }

        boolean registered = userService.registerNewUser(userRegistrationForm);


        if(!registered) {
            errors.rejectValue("username", "username.exists", "Username already exists");
            logger.info("Username {} already exists", userRegistrationForm.getUsername());
            return "userRegistrationForm";
        }

        logger.info("User {} registered successfully", userRegistrationForm.getUsername());
        return "redirect:/login";
    }
}
