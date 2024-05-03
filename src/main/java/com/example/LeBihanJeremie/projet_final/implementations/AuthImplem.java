package com.example.LeBihanJeremie.projet_final.implementations;

import com.example.LeBihanJeremie.projet_final.models.Admin;
import com.example.LeBihanJeremie.projet_final.models.Role;
import com.example.LeBihanJeremie.projet_final.models.User;
import com.example.LeBihanJeremie.projet_final.security.JwtService;
import com.example.LeBihanJeremie.projet_final.services.AdminService;
import com.example.LeBihanJeremie.projet_final.services.AuthService;
import com.example.LeBihanJeremie.projet_final.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthImplem implements AuthService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AdminService adminService;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;

    @Override
    public String login(Admin user, String password) {
        if(bCryptPasswordEncoder.matches(password, user.getPassword()))
            return jwtService.generateToken(user);
        return null;
    }

    @Override
    public Admin registerAdmin(Admin entity, Role role) {
        String passwordEncoded = bCryptPasswordEncoder.encode(entity.getPassword());
        entity.setPassword(passwordEncoded);
        adminService.addRoleToAdmin(entity, role);
        return adminService.createAdmin(entity);
    }

    @Override
    public User registerUser(User entity, Role role) {
        String passwordEncoded = bCryptPasswordEncoder.encode(entity.getPassword());
        entity.setPassword(passwordEncoded);
        userService.addRoleToUser(entity, role);
        return userService.createUser(entity);
    }
}
