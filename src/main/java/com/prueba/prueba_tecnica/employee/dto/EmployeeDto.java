package com.prueba.prueba_tecnica.employee.dto;

import com.prueba.prueba_tecnica.employee.domain.Area;
import com.prueba.prueba_tecnica.employee.domain.DocumentType;
import jakarta.validation.constraints.NotNull;

public record EmployeeDto(
        Long id,
        @NotNull DocumentType documentType,
        @NotNull String documentNumber,
        @NotNull String names,
        @NotNull String surnames,
        @NotNull Area area,
        @NotNull String email,
        @NotNull String phone
)
{}
