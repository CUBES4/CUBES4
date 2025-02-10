package com.cubes4.CUBES4.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

/**
 * Data Transfer Object pour la classe Customer.
 * Permet de limiter les données échangées via l'API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {

    private Long id;

    @NotNull(message = "Le nom ne peut pas être nul")
    @Size(min = 1, max = 100, message = "Le nom doit contenir entre 1 et 100 caractères")
    private String lastName;

    @NotNull(message = "Le prénom ne peut pas être nul")
    @Size(min = 1, max = 100, message = "Le prénom doit contenir entre 1 et 100 caractères")
    private String firstName;

    @NotNull(message = "L'adresse e-mail est obligatoire")
    @Email(message = "L'adresse e-mail doit être valide")
    private String email;

    @Size(max = 255, message = "L'adresse ne doit pas dépasser 255 caractères")
    private String address;

    @Size(max = 15, message = "Le numéro de téléphone ne doit pas dépasser 15 caractères")
    private String phoneNumber;

    private List<OrderDTO> orders;
}
