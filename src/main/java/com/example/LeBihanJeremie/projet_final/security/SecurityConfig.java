package com.example.LeBihanJeremie.projet_final.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( registry -> {
                    registry
                            .anyRequest().permitAll();

                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

/*.requestMatchers("/admin/login").permitAll()
                            .requestMatchers("/admin/**").hasAuthority("ADMIN")
                            .requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/user/**").hasAuthority("USER")

                            .requestMatchers(HttpMethod.POST, "/admin/login", "/admin/register").permitAll()
                            .requestMatchers("/**").permitAll().anyRequest().authenticated();*/