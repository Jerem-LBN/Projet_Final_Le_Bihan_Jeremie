package com.example.LeBihanJeremie.projet_final.controllers;

import com.example.LeBihanJeremie.projet_final.models.User;
import com.example.LeBihanJeremie.projet_final.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserService userService;

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        System.out.println("delete user");
        Optional<User> user = userService.getUserById(id);
        System.out.println(user);
        if(user.isEmpty())
            return new ResponseEntity<>(
                    "User not found",
                    HttpStatus.NOT_FOUND
            );
        return new ResponseEntity<>(
                userService.deleteUserById(id),
                HttpStatus.OK
        );
    }

    @PutMapping("/update/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User entity){
        Optional<User> user = userService.getUserById(id);
        if(user.isEmpty())
            return new ResponseEntity<>(
                    "User not found",
                    HttpStatus.NOT_FOUND
            );
        return new ResponseEntity<>(
                userService.updateUser(id, entity),
                HttpStatus.OK
        );
    }
}
