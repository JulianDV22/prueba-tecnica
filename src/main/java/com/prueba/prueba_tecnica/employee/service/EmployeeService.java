package com.prueba.prueba_tecnica.employee.service;

import com.prueba.prueba_tecnica.employee.domain.DocumentType;
import com.prueba.prueba_tecnica.employee.domain.EmployeeDomain;
import com.prueba.prueba_tecnica.employee.repo.EmployeeRepository;
import com.prueba.prueba_tecnica.shared.error.NotFoundException;
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
                .orElseThrow(() -> new NotFoundException("Empleado no encontrado con id: " + id));
    }

    @Transactional(readOnly = true)
    public EmployeeDomain getByDocument(DocumentType documentType, String documentNumber) {
        return employeeRepository.findByDocumentTypeAndDocumentNumber(
                documentType, documentNumber
        ).orElseThrow(() -> new NotFoundException("Empleado no encontrado con documento: " + documentType + " " + documentNumber));
    }

    @Transactional(readOnly = true)
    public List<EmployeeDomain> listAll(Sort sort) {
        return employeeRepository.findAll(sort == null ? Sort.by("names").ascending() : sort);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDomain> findByNames(String names, Sort sort) {
        try {
            return employeeRepository.findByNamesContainingIgnoreCase(
                    names == null ? "" : names.trim(),
                    sort == null ? Sort.by("name").ascending() : sort
            );
        } catch (Exception e) {
            throw new NotFoundException("Error al procesar el parámetro 'names': " + e.getMessage());
        }

    }

    @Transactional(readOnly = true)
    public List<EmployeeDomain> listByDocumentType(DocumentType documentType, Sort sort) {
        try {
            return employeeRepository.findByDocumentType(
                    documentType,
                    sort == null ? Sort.by("id") : sort
            );
        } catch (Exception e) {
            throw new NotFoundException("Error al procesar el parámetro 'documentType': " + e.getMessage());
        }

    }

    @Transactional(readOnly = true)
    public boolean existsByDoc(DocumentType documentType, String documentNumber) {
        try {
            if (documentType == null || documentNumber == null || documentNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("El tipo y número de documento no pueden ser nulos o vacíos");
            }
            return employeeRepository.existsByDocumentTypeAndDocumentNumber(documentType, documentNumber.trim());
        } catch (Exception e) {
            throw new NotFoundException("Error al procesar los parámetros 'documentType' o 'documentNumber': " + e.getMessage());
        }
    }
}
