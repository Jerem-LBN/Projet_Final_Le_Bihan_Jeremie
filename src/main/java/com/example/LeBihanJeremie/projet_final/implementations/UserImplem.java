package com.example.LeBihanJeremie.projet_final.implementations;

import com.example.LeBihanJeremie.projet_final.models.Role;
import com.example.LeBihanJeremie.projet_final.repositories.UserRepo;
import com.example.LeBihanJeremie.projet_final.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.LeBihanJeremie.projet_final.models.User;

import java.util.Optional;

@Service
public class UserImplem implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public void addRoleToUser(User user, Role role){
        user.addRole(role);
        userRepo.save(user);
    }

    @Override
    public Optional<User> getUSerByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public User deleteUserById(Long id) {
        User user = userRepo.findById(id).get();
        userRepo.deleteById(id);
        return user;
    }

    @Override
    public User updateUser(Long id, User entity) {
        User user = userRepo.findById(id).get();
        String passwordEncoded = bCryptPasswordEncoder.encode(entity.getPassword());
        user.setEmail(entity.getEmail());
        user.setPassword(passwordEncoded);
        return userRepo.save(user);
    }
}
