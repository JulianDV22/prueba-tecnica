package com.prueba.prueba_tecnica.travel.repo;

import com.prueba.prueba_tecnica.travel.domain.TravelDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelRepository extends JpaRepository<TravelDomain, Long> {

    Optional<TravelDomain> findByDestinationContainingIgnoreCase(String destination);

    Optional<TravelDomain> findById(Long id);
}
