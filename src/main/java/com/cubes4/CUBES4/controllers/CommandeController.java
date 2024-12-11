package com.cubes4.CUBES4.controllers;

import com.cubes4.CUBES4.models.Commande;
import com.cubes4.CUBES4.repositories.CommandeRepository;
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
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeRepository commandeRepository;

    @GetMapping
    public List<Commande> getAllClients() {
        return commandeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getClientById(@PathVariable Long id) {
        return commandeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Commande> createFamille(@RequestBody Commande commande) {
        Commande savedFamille = commandeRepository.save(commande);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFamille);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateClient(@PathVariable Long id, @RequestBody Commande updatedCommande) {
        return commandeRepository.findById(id)
                .map(commande -> {
                    commande.setClient(updatedCommande.getClient());
                    commande.setDateCommande(updatedCommande.getDateCommande());
                    commande.setFournisseur(updatedCommande.getFournisseur());
                    commande.setLigneCommandes(updatedCommande.getLigneCommandes());
                    commande.setFournisseurCommande(updatedCommande.isFournisseurCommande());
                    commande.setStatut(updatedCommande.getStatut());
                    Commande saved = commandeRepository.save(commande);
                    return ResponseEntity.ok(saved);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        return commandeRepository.findById(id)
                .map(commande -> {
                    commandeRepository.delete(commande);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
