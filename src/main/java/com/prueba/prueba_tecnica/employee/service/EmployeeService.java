package com.prueba.prueba_tecnica.employee.service;

import com.prueba.prueba_tecnica.employee.domain.DocumentType;
import com.prueba.prueba_tecnica.employee.domain.EmployeeDomain;
import com.prueba.prueba_tecnica.employee.repo.EmployeeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public EmployeeDomain get(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con ID: " + id));
    }

    @Transactional(readOnly = true)
    public EmployeeDomain getByDocument(DocumentType documentType, String documentNumber) {
        return employeeRepository.findByDocumentTypeAndDocumentNumber(
                documentType, documentNumber
        ).orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con documento: " + documentType + " " + documentNumber));
    }

    @Transactional(readOnly = true)
    public List<EmployeeDomain> listAll(Sort sort) {
        return employeeRepository.findAll(sort == null ? Sort.by("id").ascending() : sort);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDomain> findByNames(String names, Sort sort) {
        return employeeRepository.findByNamesContainingIgnoreCase(
                names == null ? "" : names.trim(),
                sort == null ? Sort.by("name").ascending() : sort
        );
    }

    @Transactional(readOnly = true)
    public Slice<EmployeeDomain> findByNamePaged(String names, Pageable pageable) {
        return employeeRepository.findByNamesContainingIgnoreCase(
                names == null ? "" : names.trim(),
                pageable
        );
    }

    @Transactional(readOnly = true)
    public List<EmployeeDomain> listByDocumentType(DocumentType documentType, Sort sort) {
        return employeeRepository.findByDocumentType(
                documentType,
                sort == null ? Sort.by("id") : sort
        );
    }

    @Transactional(readOnly = true)
    public boolean existsByDoc(DocumentType documentType, String documentNumber) {
        if (documentType == null || documentNumber == null || documentNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo y número de documento no pueden ser nulos o vacíos");
        }
        return employeeRepository.existsByDocumentTypeAndDocumentNumber(documentType, documentNumber.trim());
    }
}
