package com.prueba.prueba_tecnica.bills.repo;

import com.prueba.prueba_tecnica.bills.domain.BillDomain;
import com.prueba.prueba_tecnica.bills.view.BillItemView;
import com.prueba.prueba_tecnica.bills.view.EmployeeBillView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BillRepository extends JpaRepository<BillDomain, Long> {
    @Query("""
            select   b.id as id,
                     e.documentNumber as employeeDocumentNumber,
                     e.names as employeeNames,
                     t.destination as travelDestination,
                     b.amount as amount,
                     b.startDate as startDate,
                     b.durationDays as durationDays,
                     b.endDate as endDate
            from BillDomain b
            join b.employee e
            join b.travel t
            where lower(t.destination) like lower(concat('%', :q, '%'))
            order by e.names asc, e.surnames asc, b.startDate asc
            """)
    List<BillItemView> findBillByTravelDestination(@Param("q") String q);

    @Query("""
            select   b.id as id,
                     e.documentNumber as employeeDocumentNumber,
                     e.names as employeeNames,
                     t.destination as travelDestination,
                     b.amount as amount,
                     b.startDate as startDate,
                     b.durationDays as durationDays,
                     b.endDate as endDate
            from BillDomain b
            join b.employee e
            join b.travel t
            where lower(e.names) like lower(concat('%', :q, '%'))
            order by e.names asc, e.surnames asc, b.startDate asc
            """)
    List<BillItemView> findBillByEmployeeNames(@Param("q") String q);

    @Query("""
            select   b.id as id,
                     e.documentNumber as employeeDocumentNumber,
                     e.names as employeeNames,
                     t.destination as travelDestination,
                     b.amount as amount,
                     b.startDate as startDate,
                     b.durationDays as durationDays,
                     b.endDate as endDate
            from BillDomain b
            join b.employee e
            join b.travel t
            where lower(e.documentNumber) like lower(concat('%', :q, '%'))
            order by e.names asc, e.surnames asc, b.startDate asc
            """)
    List<BillItemView> findBillByEmployeeDocumentNumber(@Param("q") String q);

    @Query("""
            select   b.id as id,
                     e.documentNumber as employeeDocumentNumber,
                     e.names as employeeNames,
                     e.surnames as employeeSurnames,
                     t.destination as travelDestination,
                     b.amount as amount,
                     b.startDate as startDate,
                     b.durationDays as durationDays,
                     b.endDate as endDate
            from BillDomain b
            join b.employee e
            join b.travel t
            order by e.names asc, b.startDate asc
            """)
    List<BillItemView> findAllBills();

    @Query("select coalesce(sum(b.amount), 0) from BillDomain b")
    Long getTotalAmountOfAllBills();

    @Query("""
            select e.documentNumber as employeeDocumentNumber,
                   e.names as employeeNames,
                   e.surnames as employeeSurnames,
                   YEAR(b.endDate) as year,
                   MONTH(b.endDate) as month,
                   sum(b.amount) as totalAmount,
                   case when sum(b.amount) > 1000000 then 'SURA'
                        else 'EMPLEADO' end as responsible
            from BillDomain b
            join b.employee e
            group by e.id, e.documentNumber, e.names, e.surnames, YEAR(b.endDate), MONTH(b.endDate)
            order by e.names asc, e.surnames asc, year asc, month asc
            """)
    List<EmployeeBillView> findTotalByEmployeeAndMonth();

}

