package com.cubes4.CUBES4.dto;

import com.cubes4.CUBES4.models.Order;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Data Transfer Object pour la classe Customer.
 * Permet de limiter les données échangées via l'API.
 */
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

    // Liste des identifiants des commandes associées
    private List<OrderDTO> orders;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Long> getOrders() {
        return orders;
    }

    public void setOrders(List<Long> orders) {
        this.orders = orders;
    }
}
