package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Client;
import com.cubes4.CUBES4.repositories.ClientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private ClientRepository clientRepository;

    @Operation(summary = "Récupérer tous les clients", description = "Renvoie la liste complète des clients.")
    @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Operation(summary = "Récupérer un client par ID", description = "Renvoie les détails d'un client.")
    @ApiResponse(responseCode = "200", description = "Client trouvé")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Créer un nouveau client", description = "Ajoute un nouveau client dans la base de données.")
    @ApiResponse(responseCode = "201", description = "Client créé avec succès")
    @PostMapping
    public ResponseEntity<Client> createFamille(@RequestBody Client client) {
        Client savedFamille = clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFamille);
    }

    @Operation(summary = "Mettre à jour un client", description = "Modifie les informations d'un client existant.")
    @ApiResponse(responseCode = "200", description = "Client mis à jour avec succès")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setNom(updatedClient.getNom());
                    client.setPrenom(updatedClient.getPrenom());
                    client.setEmail(updatedClient.getEmail());
                    client.setAdresse(updatedClient.getAdresse());
                    client.setCommandes(updatedClient.getCommandes());
                    Client saved = clientRepository.save(client);
                    return ResponseEntity.ok(saved);
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Supprimer un client", description = "Supprime un client existant par ID.")
    @ApiResponse(responseCode = "204", description = "Client supprimé avec succès")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
