package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    List<SupplierDTO> getAllSuppliers();

    SupplierDTO getSupplierById(Long id);

    SupplierDTO createSupplier(SupplierDTO supplierDTO);

    SupplierDTO updateSupplier(Long id, SupplierDTO updatedSupplierDTO);

    void deleteSupplier(Long id);

    List<String> getAllSupplierNames();
}
