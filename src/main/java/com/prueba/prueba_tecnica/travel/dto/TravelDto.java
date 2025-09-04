package com.prueba.prueba_tecnica.travel.dto;

import jakarta.validation.constraints.NotNull;

public class TravelDto {
    Long id;
    @NotNull String destination;
}
