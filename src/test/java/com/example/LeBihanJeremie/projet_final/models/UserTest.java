package com.example.LeBihanJeremie.projet_final.models;

import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Set;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserTest {

    @Autowired
    private LocalValidatorFactoryBean validator;
    @Test
    public void shouldthrowConstraintsValidationWithPasswordIsWrong(){
        User user = new User(null, "Jean", "Jean Dujardin", "jean@gmail.com", "5642", null, false, null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty());

        boolean found = false;
        for(ConstraintViolation<User> violation : violations){
            if(violation.getMessage().equals("Password must contain at least one uppercase letter, one lowercase letter and one number")){
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
}
