package com.prueba.prueba_tecnica.travel.service;

import com.prueba.prueba_tecnica.shared.error.NotFoundException;
import com.prueba.prueba_tecnica.travel.domain.TravelDomain;
import com.prueba.prueba_tecnica.travel.repo.TravelRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TravelService {
    private final TravelRepository travelRepository;

    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    @Transactional(readOnly = true)
    public TravelDomain get(Long id) {
        return travelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Viaje no encontrado con id: " + id));
    }

    @Transactional(readOnly = true)
    public TravelDomain getByDestinationContainingIgnoreCase(String destination) {
        return travelRepository.findByDestinationContainingIgnoreCase(destination)
                .orElseThrow(() -> new NotFoundException("Viaje no encontrado con destino: " + destination));
    }

    @Transactional(readOnly = true)
    public List<TravelDomain> listAll(Sort sort) {
        return travelRepository.findAll(sort == null ? Sort.by("id").ascending() : sort);
    }

}
