package com.prueba.prueba_tecnica.bills.view;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BillItemView {
    Long getId();
    String getEmployeeDocumentNumber();
    String getEmployeeNames();
    String getTravelDestination();
    BigDecimal getAmount();
    LocalDate getStartDate();
    Integer getDurationDays();
    LocalDate getEndDate();
}
