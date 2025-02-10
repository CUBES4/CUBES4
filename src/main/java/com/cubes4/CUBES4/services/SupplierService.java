package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.SupplierDTO;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface SupplierService {

    List<SupplierDTO> getAllSuppliers();

    SupplierDTO getSupplierById(Long id);

    SupplierDTO createSupplier(SupplierDTO supplier);

    SupplierDTO updateSupplier(Long id, SupplierDTO updatedSupplier);

    void deleteSupplier(Long id);
}
