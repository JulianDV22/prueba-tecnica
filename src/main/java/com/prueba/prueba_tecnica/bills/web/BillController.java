package com.prueba.prueba_tecnica.bills.web;

import com.prueba.prueba_tecnica.bills.domain.BillDomain;
import com.prueba.prueba_tecnica.bills.dto.EmployeeBillDto;
import com.prueba.prueba_tecnica.bills.service.BillService;
import com.prueba.prueba_tecnica.bills.view.BillItemView;
import com.prueba.prueba_tecnica.bills.view.EmployeeBillView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;

    @GetMapping("/by-travel-destination")
    public List<BillItemView> findBillsByTravelDestination(@RequestParam("q") String q) {
        return billService.getBillsByTravelDestination(q);
    }

    @GetMapping("/by-employee-names")
    public List<BillItemView> findBillsByEmployeeNames(@RequestParam("q") String q) {
        return billService.getBillsByEmployeeNames(q);
    }

    @GetMapping("/by-employee-document-number")
    public List<BillItemView> findBillsByEmployeeDocumentNumber(@RequestParam("q") String q) {
        return billService.getBillsByEmployeeDocumentNumber(q);
    }

    @GetMapping()
    public List<BillItemView> findAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/total-amount")
    public ResponseEntity<Long> getTotalAmountOfAllBills() {
        return ResponseEntity.ok(billService.getTotalAmountOfAllBills());
    }

    @GetMapping("/total-by-employee-month")
    public List<EmployeeBillDto> getTotalByEmployeeAndMonth() {
        return billService.getTotalByEmployeeAndMonth();
    }
}
