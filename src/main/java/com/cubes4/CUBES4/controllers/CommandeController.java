package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Commande;
import com.cubes4.CUBES4.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Commande> getAllClients() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getClientById(@PathVariable Long id) {
        Commande commande = commandeService.getCommandeById(id);
        return ResponseEntity.ok(commande);
    }

    @GetMapping("/statut")
    public ResponseEntity<List<Commande>> getCommandeByStatus(@RequestParam Commande.CommandeStatus status) {
        List<Commande> commandes = commandeService.getCommandeByStatut(status);
        return ResponseEntity.ok(commandes);
    }

    @PostMapping
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commande) {
        Commande savedFamille = commandeService.createCommande(commande);
        return ResponseEntity.ok(savedFamille);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateClient(@PathVariable Long id, @RequestBody Commande updatedCommande) {
        Commande savedCommande = commandeService.updateCommande(id, updatedCommande);
        return ResponseEntity.ok(savedCommande);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }

}
