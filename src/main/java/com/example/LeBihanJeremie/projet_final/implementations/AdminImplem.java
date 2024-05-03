package com.example.LeBihanJeremie.projet_final.implementations;

import com.example.LeBihanJeremie.projet_final.models.Admin;
import com.example.LeBihanJeremie.projet_final.models.Role;
import com.example.LeBihanJeremie.projet_final.repositories.AdminRepo;
import com.example.LeBihanJeremie.projet_final.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminImplem implements AdminService {
    @Autowired
    AdminRepo adminRepo;
    @Override
    public Admin createAdmin(Admin entity) {
        return adminRepo.save(entity);
    }

    @Override
    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepo.findByEmail(email);
    }

    @Override
    public void addRoleToAdmin(Admin entity, Role role){
        entity.addRole(role);
        adminRepo.save(entity);
    }
}
