package com.example.LeBihanJeremie.projet_final.services;

import com.example.LeBihanJeremie.projet_final.models.Role;
import com.example.LeBihanJeremie.projet_final.models.User;
import org.springframework.stereotype.Service;


public interface AuthService {
    String login(User user, String password);
    User register(User entity, Role role);
}
