package com.prueba.prueba_tecnica.bills.service;

import com.prueba.prueba_tecnica.bills.domain.BillDomain;
import com.prueba.prueba_tecnica.bills.dto.EmployeeBillDto;
import com.prueba.prueba_tecnica.bills.repo.BillRepository;
import com.prueba.prueba_tecnica.bills.view.BillItemView;
import com.prueba.prueba_tecnica.bills.view.EmployeeBillView;
import com.prueba.prueba_tecnica.shared.error.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;

    @Transactional(readOnly = true)
    public List<BillItemView> getBillsByTravelDestination(String q) {
        String term = (q == null) ? "" : q.trim();
        if(term.isEmpty()) {
            throw new BadRequestException("El parámetro 'q' no puede estar vacío");
        }
        return billRepository.findBillByTravelDestination(term);
    }

    @Transactional(readOnly = true)
    public List<BillItemView> getBillsByEmployeeNames(String q) {
        String term = (q == null) ? "" : q.trim();
        if(term.isEmpty()) {
            throw new BadRequestException("El parámetro 'q' no puede estar vacío");
        }
        return billRepository.findBillByEmployeeNames(term);
    }

    @Transactional(readOnly = true)
    public List<BillItemView> getBillsByEmployeeDocumentNumber(String q) {
        String term = (q == null) ? "" : q.trim();
        if(term.isEmpty()) {
            throw new BadRequestException("El parámetro 'q' no puede estar vacío");
        }
        return billRepository.findBillByEmployeeDocumentNumber(term);
    }

    @Transactional(readOnly = true)
    public List<BillItemView> getAllBills() {
        return billRepository.findAllBills();
    }

    @Transactional(readOnly = true)
    public Long getTotalAmountOfAllBills() {
        return billRepository.getTotalAmountOfAllBills();
    }

    @Transactional(readOnly = true)
    public List<EmployeeBillDto> getTotalByEmployeeAndMonth() {
        return billRepository.findTotalByEmployeeAndMonth().stream()
                .map(v -> new EmployeeBillDto(
                        v.getEmployeeDocumentNumber(),
                        v.getEmployeeNames(),
                        v.getEmployeeSurnames(),
                        v.getYear(),
                        v.getMonth(),
                        v.getTotalAmount(),
                        v.getTotalAmount() > 1000000 ? "SURA" : "EMPLEADO"
                ))
                .toList();
    }


}
