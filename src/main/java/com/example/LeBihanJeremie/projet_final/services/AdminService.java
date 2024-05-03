package com.example.LeBihanJeremie.projet_final.services;

import com.example.LeBihanJeremie.projet_final.models.Admin;
import com.example.LeBihanJeremie.projet_final.models.Role;
import com.example.LeBihanJeremie.projet_final.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface AdminService {
    void addRoleToAdmin(Admin entity, Role role);

    Admin createAdmin(Admin entity);

    Optional<Admin> getAdminByEmail(String email);
}
