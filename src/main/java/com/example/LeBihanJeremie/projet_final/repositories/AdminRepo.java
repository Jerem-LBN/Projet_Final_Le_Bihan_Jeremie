package com.example.LeBihanJeremie.projet_final.repositories;

import com.example.LeBihanJeremie.projet_final.models.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo {
    Admin save(Admin admin);
}
