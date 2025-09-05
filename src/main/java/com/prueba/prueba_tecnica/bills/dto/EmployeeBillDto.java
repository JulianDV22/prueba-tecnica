package com.prueba.prueba_tecnica.bills.dto;

public record EmployeeBillDto (
        String employeeDocumentNumber,
        String employeeNames,
        String employeeSurnames,
        Integer year,
        Integer month,
        Double totalAmount,
        String responsible
){
}
