package com.example.LeBihanJeremie.projet_final.controllers;

import com.example.LeBihanJeremie.projet_final.models.Admin;
import com.example.LeBihanJeremie.projet_final.models.Role;
import com.example.LeBihanJeremie.projet_final.models.User;
import com.example.LeBihanJeremie.projet_final.repositories.RoleRepo;
import com.example.LeBihanJeremie.projet_final.services.AdminService;
import com.example.LeBihanJeremie.projet_final.services.AuthService;
import com.example.LeBihanJeremie.projet_final.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    AdminService adminService;

    private ResponseEntity<?> userExisteResponse (User entity){
        Optional<User> user = userService.getUSerByEmail(entity.getEmail());
        if(user.isPresent())
            return new ResponseEntity<>(
                    "email existe déjà",
                    HttpStatus.CONFLICT
            );
        return null;
    }
    private ResponseEntity<?> adminExisteResponse (Admin entity){
        Optional<Admin> admin = adminService.getAdminByEmail(entity.getEmail());
        if(admin.isPresent())
            return new ResponseEntity<>(
                    "email existe déjà",
                    HttpStatus.CONFLICT
            );
        return null;
    }

    private String generateCode() {
        return String.valueOf(Math.random() * 10000);
    }

    @PostMapping("admin/signup")
    public ResponseEntity<?> adminSignup(@Valid @RequestBody Admin entity){
        ResponseEntity<?> res = adminExisteResponse(entity);
        if (res != null)
            return res;
        Optional<Role> role = roleRepo.findByRoleName(Role.RoleEnum.ADMIN.name());
        if(role.isEmpty())
            return new ResponseEntity<>(
                    "Une erreur est servenue !",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        return new ResponseEntity<>(
                authService.registerAdmin(entity, role.get()),
                HttpStatus.CREATED
        );
    }

    @PostMapping("admin/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody User entity){
        ResponseEntity<?> res = userExisteResponse(entity);
        if (res != null)
            return res;
        Optional<Role> role = roleRepo.findByRoleName(Role.RoleEnum.USER.name());
        if(role.isEmpty())
            return new ResponseEntity<>(
                    "Une erreur est servenue !",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        entity.setCode(generateCode());
        return new ResponseEntity<>(
                authService.registerUser(entity, role.get()),
                HttpStatus.CREATED
        );
    }

    @PostMapping("admin/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request){
        String email = request.get("email");
        String password = request.get("password");
        Optional<Admin> admin = adminService.getAdminByEmail(email);
        if (admin.isEmpty())
            return new ResponseEntity(
                    "Admin n'existe pas",
                    HttpStatus.NOT_FOUND
            );
        String jwt = authService.login(admin.get(), password);
        if (jwt == null)
            return new ResponseEntity<>(
                    "Mot de passe incorrect",
                    HttpStatus.FORBIDDEN
            );
        return new ResponseEntity<>(
                jwt,
                HttpStatus.OK
        );
    }

    @PostMapping("user/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request){
        String email = request.get("email");
        String password = request.get("password");
        Optional<User> user = userService.getUSerByEmail(email);
        if (user.isEmpty())
            return new ResponseEntity(
                    "User n'existe pas",
                    HttpStatus.NOT_FOUND
            );
        String jwt = authService.loginUser(user.get(), password);
        if (jwt == null)
            return new ResponseEntity<>(
                    "Mot de passe incorrect",
                    HttpStatus.FORBIDDEN
            );
        return new ResponseEntity<>(
                jwt,
                HttpStatus.OK
        );
    }
}
