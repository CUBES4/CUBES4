package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.models.Fournisseur;
import com.cubes4.CUBES4.repositories.FournisseurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    public Fournisseur getFournisseurById(Long id) {
        return fournisseurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur not found with id:" + id));
    }

    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.saveAndFlush(fournisseur);
    }

    public Fournisseur updateFournisseur(Long id, Fournisseur updatedFournisseur) {
        return fournisseurRepository.findById(id)
                .map(fournisseur -> {
                    fournisseur.setNom(updatedFournisseur.getNom());
                    fournisseur.setEmail(updatedFournisseur.getEmail());
                    fournisseur.setTelephone(updatedFournisseur.getTelephone());
                    fournisseur.setAdresse(updatedFournisseur.getAdresse());
                    fournisseur.setArticles(updatedFournisseur.getArticles());
                    return fournisseurRepository.saveAndFlush(fournisseur);
                }).orElseThrow(() -> new EntityNotFoundException("Fournisseur not found with id:" + id));
    }

    public void deleteFournisseur(Long id) {
        Fournisseur fournisseur = getFournisseurById(id);
        fournisseurRepository.delete(fournisseur);
    }
}
