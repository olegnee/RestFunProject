package com.oleg.nee.somerestapiproject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oleg.nee.somerestapiproject.DTO.UserDTO;
import com.oleg.nee.somerestapiproject.entity.User;
import com.oleg.nee.somerestapiproject.repository.UserRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "some main methods")
@Slf4j
@AllArgsConstructor
@RestController
public class MainController {

    private final UserRepo userRepo;
    private final ObjectMapper objectMapper;

    @Operation(
            summary = "Receive some info and creates new User",
            description = "With the help of DTO class, this method should receive some general info about class," +
                                "such as: first, last names and email."    )


    @PostMapping("/api/add")
    public void addUser(@RequestBody UserDTO userDTO) {
        log.info("New row added: " + userRepo.save(
                User.builder()
                        .firstName(userDTO.getFirstName())
                        .lastName(userDTO.getLastName())
                        .email(userDTO.getEmail())
                        .build()));
    }

    @SneakyThrows
    @GetMapping("/api/getAll")
    public List<User> getAllUsers () {
        return userRepo.findAll();
    }

    @GetMapping("/api/getUserById")
    public User getUser (@RequestParam int id) {
        return userRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api/delete")
    public void deleteUserById (@RequestParam int id) {
        User deletedUser = userRepo.getById(id);
        String name = deletedUser.getFirstName();
        userRepo.deleteById(id);

        log.info("User deleted " + name);
    }

    @PutMapping("/api/editUser")
    public String editUser (@RequestBody User user) {
        if(!userRepo.existsById(user.getId())) {
            return "There is no such user.";
        }
        log.info("User's data was edited" + userRepo.save(user));
        return userRepo.save(user).toString();
    }
}
