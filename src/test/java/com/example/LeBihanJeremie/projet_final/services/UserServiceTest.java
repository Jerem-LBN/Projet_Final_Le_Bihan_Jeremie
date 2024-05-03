package com.example.LeBihanJeremie.projet_final.services;

import com.example.LeBihanJeremie.projet_final.implementations.UserImplem;
import com.example.LeBihanJeremie.projet_final.models.User;
import com.example.LeBihanJeremie.projet_final.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Mock
    UserRepo userRepo;

    @InjectMocks
    UserImplem userImplem;

    @Test
    public void shouldGetUserByEmail(){
        User user = new User(null, "Jean", "Jean Dujardin", "jean@gmail.com", "5642", null, false, null);
        when(userRepo.findByEmail("jean@gmail.com")).thenReturn(Optional.of(user));
        Optional<User> user1 = userImplem.getUSerByEmail("jean@gmail.com");
        assert(user1.isPresent());
        assertEquals(user1.get().getEmail(), user.getEmail());
    }

}
