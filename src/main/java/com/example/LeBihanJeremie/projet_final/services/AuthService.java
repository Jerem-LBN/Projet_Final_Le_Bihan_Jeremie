package com.example.LeBihanJeremie.projet_final.services;

import com.example.LeBihanJeremie.projet_final.models.Admin;
import com.example.LeBihanJeremie.projet_final.models.Role;
import com.example.LeBihanJeremie.projet_final.models.User;
import org.springframework.stereotype.Service;


public interface AuthService {
    String login(Admin user, String password);
    Admin registerAdmin(Admin entity, Role role);

    User registerUser(User entity, Role role);

    String loginUser(User user, String password);
}
