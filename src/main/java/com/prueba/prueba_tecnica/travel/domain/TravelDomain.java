package com.prueba.prueba_tecnica.travel.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "travels")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class TravelDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destination", nullable = false, length = 100)
    private String destination;
}
