package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Client;
import com.cubes4.CUBES4.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Récupérer tous les clients", description = "Renvoie la liste complète des clients.")
    @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Récupérer un client par ID", description = "Renvoie les détails d'un client.")
    @ApiResponse(responseCode = "200", description = "Client trouvé")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @Operation(summary = "Rechercher des clients par nom ou prenom", description = "Récupère tous les clients avec le nom ou prenom spécifié.")
    @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    @GetMapping("/recherche")
    public ResponseEntity<List<Client>> getClientByNom(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String prenom) {

        List<Client> clients;

        if (nom != null) {
            clients = clientService.getClientByNom(nom);
        } else if (prenom != null) {
            clients = clientService.getClientByPrenom(prenom);
        } else {
            clients = clientService.getAllClients();
        }

        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Rechercher des clients par email", description = "Récupère tous les clients avec l'email spécifié.")
    @ApiResponse(responseCode = "201", description = "Liste des clients retournée avec succès")
    @GetMapping("/recherche/email")
    public ResponseEntity<List<Client>> getClientByEmail(String email) {
        List<Client> clients = clientService.getClientByEmail(email);
        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Rechercher des clients par numéro de télephone", description = "Récupère tous les clients avec le numéro de téléphone spécifié.")
    @ApiResponse(responseCode = "201", description = "Liste des clients retournée avec succès")
    @GetMapping("/recherche/telephone")
    public ResponseEntity<List<Client>> getClientByTelephone(String telephone) {
        List<Client> clients = clientService.getClientByTelephone(telephone);
        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Créer un nouveau client", description = "Ajoute un nouveau client dans la base de données.")
    @ApiResponse(responseCode = "201", description = "Client créé avec succès")
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedFamille = clientService.createClient(client);
        return ResponseEntity.ok(savedFamille);
    }

    @Operation(summary = "Mettre à jour un client", description = "Modifie les informations d'un client existant.")
    @ApiResponse(responseCode = "200", description = "Client mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client savedClient = clientService.updateClient(id, updatedClient);
        return ResponseEntity.ok(savedClient);
    }

    @Operation(summary = "Supprimer un client", description = "Supprime un client existant par ID.")
    @ApiResponse(responseCode = "204", description = "Client supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
