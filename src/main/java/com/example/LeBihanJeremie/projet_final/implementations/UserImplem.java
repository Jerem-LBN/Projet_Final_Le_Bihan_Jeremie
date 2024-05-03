package com.example.LeBihanJeremie.projet_final.implementations;

import com.example.LeBihanJeremie.projet_final.models.Role;
import com.example.LeBihanJeremie.projet_final.repositories.UserRepo;
import com.example.LeBihanJeremie.projet_final.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.LeBihanJeremie.projet_final.models.User;

@Service
public class UserImplem implements UserService {
    @Autowired
    UserRepo userRepo;
    @Override
    public User createUser(com.example.LeBihanJeremie.projet_final.models.User entity) {
        return userRepo.save(entity);
    }

    @Override
    public void addRoleToUser(User user, Role role){
        user.addRole(role);
        userRepo.save(user);
    }
}
