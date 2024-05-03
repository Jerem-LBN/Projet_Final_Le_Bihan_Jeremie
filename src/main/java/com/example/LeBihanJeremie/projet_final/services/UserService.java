package com.example.LeBihanJeremie.projet_final.services;

import com.example.LeBihanJeremie.projet_final.models.Role;
import com.example.LeBihanJeremie.projet_final.models.User;


public interface UserService {
    void addRoleToUser(User user, Role role);
    User createUser(User entity);
}
