package com.prueba.prueba_tecnica.employee.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_employee_doc",
                columnNames = {"document_type", "document_number"}
        )
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false, length = 10)
    private DocumentType documentType;

    @Column(name = "document_number", nullable = false, unique = true, length = 20)
    private String documentNumber;

    @Column(name = "names", nullable = false, length = 50)
    private String names;

    @Column(name = "surnames", nullable = false, length = 50)
    private String surnames;

    @Enumerated(EnumType.STRING)
    @Column(name = "area", nullable = false, length = 20)
    private Area area;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "phone", length = 15)
    private String phone;
}
