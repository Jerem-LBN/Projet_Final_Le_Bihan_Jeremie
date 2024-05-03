package com.example.LeBihanJeremie.projet_final.config;

import com.example.LeBihanJeremie.projet_final.models.Admin;
import com.example.LeBihanJeremie.projet_final.models.User;
import com.example.LeBihanJeremie.projet_final.repositories.AdminRepo;
import com.example.LeBihanJeremie.projet_final.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Configuration
public class applicationConfig {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AdminRepo adminRepo;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<User> user = userRepo.findByEmail(username);
            if (user.isPresent()) {
                return user.get();
            }
            Optional<Admin> admin = adminRepo.findByEmail(username);
            if (admin.isPresent()) {
                return admin.get();
            }

            throw new UsernameNotFoundException("User not found");
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}