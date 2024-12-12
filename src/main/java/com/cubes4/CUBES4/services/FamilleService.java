package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.models.Famille;
import com.cubes4.CUBES4.repositories.FamilleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Service
public class FamilleService {

    @Autowired
    private FamilleRepository familleRepository;

    public List<Famille> getAllFamilles() {
        return familleRepository.findAll();
    }

    public Famille getFamilleById(Long id) {
        return familleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Famille not found with id:" + id));
    }

    public Famille createFamille(Famille famille) {
        return familleRepository.saveAndFlush(famille);
    }

    public Famille updateFamille(Long id, Famille updatedFamille) {
        return familleRepository.findById(id)
                .map(famille -> {
                    famille.setNom(updatedFamille.getNom());
                    famille.setArticles(updatedFamille.getArticles());
                    return familleRepository.saveAndFlush(famille);
                }).orElseThrow(() -> new EntityNotFoundException("Famille not found with id:" + id));
    }

    public void deleteFamille(Long id) {
        Famille famille = getFamilleById(id);
        familleRepository.delete(famille);
    }
}
