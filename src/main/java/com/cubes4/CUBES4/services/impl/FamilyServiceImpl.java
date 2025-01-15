package com.cubes4.CUBES4.services.impl;

import com.cubes4.CUBES4.dto.FamilyDTO;
import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.mapper.FamilyMapper;
import com.cubes4.CUBES4.models.Family;
import com.cubes4.CUBES4.repositories.FamilyRepository;
import com.cubes4.CUBES4.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyMapper familyMapper;

    @Override
    public List<FamilyDTO> getAllFamilies() {
        return familyRepository.findAll().stream()
                .map(familyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FamilyDTO getFamilyById(Long id) {
        Family family = familyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Famille introuvable avec l'ID : " + id));
        return familyMapper.toDTO(family);
    }

    @Override
    public FamilyDTO createFamily(FamilyDTO familyDTO) {
        Family family = familyMapper.toEntity(familyDTO);
        Family savedFamily = familyRepository.save(family);
        return familyMapper.toDTO(savedFamily);
    }

    @Override
    public FamilyDTO updateFamily(Long id, FamilyDTO updatedFamilyDTO) {
        Family existingFamily = familyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Famille introuvable avec l'ID : " + id));

        existingFamily.setName(updatedFamilyDTO.getName());
        Family updatedFamily = familyRepository.save(existingFamily);
        return familyMapper.toDTO(updatedFamily);
    }

    @Override
    public void deleteFamily(Long id) {
        Family existingFamily = familyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Famille introuvable avec l'ID : " + id));

        familyRepository.delete(existingFamily);
    }
}
