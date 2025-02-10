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

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyMapper familyMapper;

    public List<FamilyDTO> getAllFamily() {
        return familyRepository.findAll().stream()
                .map(familyMapper::familyToFamilyDto)
                .collect(Collectors.toList());
    }

    public FamilyDTO getFamilyById(Long id) {
        Family family = familyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Family not found with id:" + id));
        return familyMapper.familyToFamilyDto(family);
    }

    public FamilyDTO createFamily(FamilyDTO familyDTO) {
        Family family = familyMapper.familyDtoToFamily(familyDTO, null);
        familyRepository.saveAndFlush(family);
        return familyMapper.familyToFamilyDto(family);
    }

    public FamilyDTO updateFamily(Long id, FamilyDTO updatedFamily) {
        Family family = familyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Family not found with id:" + id));
        family = familyMapper.familyDtoToFamily(updatedFamily, family);
        familyRepository.saveAndFlush(family);
        return familyMapper.familyToFamilyDto(family);
    }

    public void deleteFamily(Long id) {
        Family family = familyRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Family not found with id:" + id));
        familyRepository.delete(family);
    }
}
