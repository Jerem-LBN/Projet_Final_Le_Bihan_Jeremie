package com.example.LeBihanJeremie.projet_final.repositories;

import com.example.LeBihanJeremie.projet_final.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin save(Admin admin);
}
