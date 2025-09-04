package com.prueba.prueba_tecnica.employee.web;

import com.prueba.prueba_tecnica.employee.domain.DocumentType;
import com.prueba.prueba_tecnica.employee.domain.EmployeeDomain;
import com.prueba.prueba_tecnica.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeDomain> listAll() {
        return service.listAll(null);
    }

    @GetMapping("/search")
    public List<EmployeeDomain> search(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "names", required = false) String names
    ) {
        String q = (name != null && !name.isBlank()) ? name : names;
        if (q == null || q.isBlank()) {
            throw new IllegalArgumentException("Debes enviar ?name= o ?names=");
        }
        return service.findByNames(q.trim(), org.springframework.data.domain.Sort.by("names").ascending());
    }


    @GetMapping("/{id}")
    public EmployeeDomain get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/by-doc/{documentType}/{documentNumber}")
    public EmployeeDomain getByDoc(@PathVariable DocumentType documentType, @PathVariable String documentNumber) {
        return service.getByDocument(documentType, documentNumber);
    }

    @GetMapping("/exists")
    public boolean exists(@RequestParam DocumentType documentType, @RequestParam String documentNumber) {
        return service.existsByDoc(documentType, documentNumber);
    }
}
