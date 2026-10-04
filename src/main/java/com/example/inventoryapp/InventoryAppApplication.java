package com.example.inventoryapp;

import com.example.inventoryapp.domain.User;
import com.example.inventoryapp.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * Author: Kindle Schneider
 * Date: September 29, 2026
 * Class: INFO 1541 WA
 * Project: InventoryApp - School Bookstore Inventory System
 *
 * Statement of AI Use: Portions of this work were developed with
 * assistance from an AI assistant accessed through Rool, used for
 * debugging help, concept explanations, and code review. All code
 * was written by the author and verified through testing.
 */

@SpringBootApplication
public class InventoryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(UserService userService, PasswordEncoder passwordEncoder){
        return args ->  {
            userService.createUserIfMissing(new User("admin", passwordEncoder.encode("password"),
                    "Kindle Schneider", "ROLE_ADMIN"));
            userService.createUserIfMissing(new User("user", passwordEncoder.encode("password"),
                    "John Smith", "ROLE_USER"));
            userService.createUserIfMissing(new User("associate", passwordEncoder.encode("password"),
                    "Jane Doe", "ROLE_ASSOC"));
            userService.createUserIfMissing(new User("manager", passwordEncoder.encode("password"),
                    "Linny Mayor", "ROLE_MNGR"));
        };
    }

}
