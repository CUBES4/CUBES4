package com.cubes4.CUBES4.services.impl;

import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.models.Family;
import com.cubes4.CUBES4.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Service
public class FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    public List<Family> getAllFamily() {
        return familyRepository.findAll();
    }

    public Family getFamilyById(Long id) {
        return familyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Family not found with id:" + id));
    }

    public Family createFamily(Family family) {
        return familyRepository.saveAndFlush(family);
    }

    public Family updateFamily(Long id, Family updatedFamily) {
        return familyRepository.findById(id)
                .map(family -> {
                    family.setName(updatedFamily.getName());
                    family.setArticles(updatedFamily.getArticles());
                    return familyRepository.saveAndFlush(family);
                }).orElseThrow(() -> new ResourceNotFoundException("Family not found with id:" + id));
    }

    public void deleteFamily(Long id) {
        Family family = getFamilyById(id);
        familyRepository.delete(family);
    }
}
