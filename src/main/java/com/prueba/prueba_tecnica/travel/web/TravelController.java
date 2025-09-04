package com.prueba.prueba_tecnica.travel.web;

import com.prueba.prueba_tecnica.travel.domain.TravelDomain;
import com.prueba.prueba_tecnica.travel.service.TravelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/travels")
public class TravelController {
    public final TravelService service;

    public TravelController(TravelService service) {
        this.service = service;
    }

    @GetMapping
    public List<TravelDomain> listAll() {
        return service.listAll(null);
    }

    @GetMapping("/{id}")
    public TravelDomain get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/by-destination/search")
    public TravelDomain getByDestination(@RequestParam("destination") String destination) {
        return service.getByDestinationContainingIgnoreCase(destination);
    }
}
