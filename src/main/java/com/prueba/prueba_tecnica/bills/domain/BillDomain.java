package com.prueba.prueba_tecnica.bills.domain;

import com.prueba.prueba_tecnica.employee.domain.EmployeeDomain;
import com.prueba.prueba_tecnica.travel.domain.TravelDomain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bills", indexes = {
        @Index(name = "idx_bills_employee", columnList = "employee_id"),
        @Index(name = "idx_bills_travel", columnList = "travel_id"),
        @Index(name = "idx_bills_startDate", columnList = "startDate")
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class BillDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false, foreignKey = @ForeignKey(name = "fk_bills_employee"))
    private EmployeeDomain employee;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id", nullable = false, foreignKey = @ForeignKey(name = "fk_bills_travel"))
    private TravelDomain travel;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "durationDays", nullable = false)
    private Integer durationDays;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    @PrePersist @PreUpdate
    private void calculateEndDate() {
        if (startDate != null && durationDays != null) {
            this.endDate = this.startDate.plusDays(this.durationDays - 1);
            }
    }
}
