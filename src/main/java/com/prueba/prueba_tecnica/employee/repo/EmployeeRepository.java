package com.prueba.prueba_tecnica.employee.repo;

import com.prueba.prueba_tecnica.employee.domain.DocumentType;
import com.prueba.prueba_tecnica.employee.domain.EmployeeDomain;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeDomain, Long> {

    Optional<EmployeeDomain> findByDocumentTypeAndDocumentNumber(DocumentType type, String number);

    boolean existsByDocumentTypeAndDocumentNumber(DocumentType type, String number);

    List<EmployeeDomain> findByNamesContainingIgnoreCase(String names, Sort sort);

    Slice<EmployeeDomain> findByNamesContainingIgnoreCase(String names, Pageable pageable);

    List<EmployeeDomain> findByDocumentType(DocumentType type, Sort sort);
}
