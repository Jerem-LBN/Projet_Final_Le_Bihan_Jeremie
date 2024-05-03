package com.example.LeBihanJeremie.projet_final.controllers;

import com.example.LeBihanJeremie.projet_final.models.User;
import com.example.LeBihanJeremie.projet_final.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/validate/{code}")
    public ResponseEntity<?> validateUser(@PathVariable String code) {
        Optional<User> user =  userService.getUserByCode(code);
        if (user.isEmpty())
            return new ResponseEntity<>(
                    "Code invalide",
                    HttpStatus.BAD_REQUEST
            );
        user.get().setValid(true);
        user.get().setCode("");
        return new ResponseEntity<>(
                userService.createUser(user.get()),
                HttpStatus.OK
        );
    }
}
