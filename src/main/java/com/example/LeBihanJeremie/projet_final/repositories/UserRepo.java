package com.example.LeBihanJeremie.projet_final.repositories;

import com.example.LeBihanJeremie.projet_final.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo {
    User save(User user);
}
