package com.example.LeBihanJeremie.projet_final.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.management.relation.Role;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("Admin")
public class Admin extends User{

}
