package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.models.Commande;
import com.cubes4.CUBES4.repositories.CommandeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande not found with id: " + id));
    }

    public List<Commande> getCommandeByStatut(Commande.CommandeStatus statut) {
        return commandeRepository.findByStatut(statut);
    }

    public Commande createCommande(Commande commande) {
        return commandeRepository.saveAndFlush(commande);
    }

    public Commande updateCommande(Long id, Commande updatedCommande) {
        return commandeRepository.findById(id)
                .map(commande -> {
                    commande.setDateCommande(updatedCommande.getDateCommande());
                    commande.setFournisseurCommande(updatedCommande.getFournisseur() != null);
                    commande.setFournisseur(updatedCommande.getFournisseur());
                    commande.setLigneCommandes(updatedCommande.getLigneCommandes());
                    commande.setClient(updatedCommande.getClient());
                    commande.setStatut(updatedCommande.getStatut());
                    return commandeRepository.saveAndFlush(commande);
                }).orElseThrow(() -> new EntityNotFoundException("Commande not found with id: " + id));
    }

    public void deleteCommande(Long id) {
        Commande commande = getCommandeById(id);
        commandeRepository.delete(commande);
    }
}
