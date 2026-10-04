package com.example.inventoryapp.services;

import com.example.inventoryapp.data.UserRepository;
import com.example.inventoryapp.domain.User;
import com.example.inventoryapp.security.UserRegistrationForm;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jspecify.annotations.NullMarked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository ur, PasswordEncoder pe) {
        userRepo = ur;
        passwordEncoder = pe;
    }

    @Override
    public @NullMarked UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found" + username));
    }

    public void createUserIfMissing(User user){
        if(!userRepo.existsByUsername(user.getUsername())){
            logger.info("Creating user with username " + user.getUsername());
            userRepo.save(user);
        }

        else{
            logger.info("User already exists", user.getUsername());
        }
    }

    @Transactional
    public boolean registerNewUser(UserRegistrationForm form) {
        String username = form.getUsername().trim();
        if(userRepo.existsByUsername(username)){
            return false;
        }

        String encodedPassword = passwordEncoder.encode(form.getPassword());

        User newUser = new User(username, encodedPassword, form.getFullName(), "ROLE_USER");
        userRepo.save(newUser);
        return true;
    }
}
