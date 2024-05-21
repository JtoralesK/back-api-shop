package com.apifrontGroup.shopFrontApiRest.repository;

import com.apifrontGroup.shopFrontApiRest.dto.WeekSInvoice;
import com.apifrontGroup.shopFrontApiRest.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    @Query("SELECT i FROM Invoice i WHERE i.costumer LIKE %:searchTerm%")
    Page<Invoice> findInvoicesByCostumer(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT COUNT(*) FROM Invoice ")
    Long getInvoicesCount();

    @Query(value = "SELECT COUNT(*) AS cant FROM invoices WHERE DATE(created_at) = CURDATE()", nativeQuery = true)
    Long getTodayInvoicesCount();

    @Query(value = "SELECT DATE_SUB(CURDATE(), INTERVAL days_a_restar DAY) as date,\n" +
            "       COUNT(i.id) as totalInvoices\n" +
            "FROM (\n" +
            "    SELECT 0 as days_a_restar\n" +
            "    UNION SELECT 1\n" +
            "    UNION SELECT 2\n" +
            "    UNION SELECT 3\n" +
            "    UNION SELECT 4\n" +
            "    UNION SELECT 5\n" +
            "    UNION SELECT 6\n" +
            ") AS dates\n" +
            "LEFT JOIN invoices i \n" +
            "ON DATE(i.created_at) = DATE_SUB(CURDATE(), INTERVAL days_a_restar DAY)\n" +
            "GROUP BY date order by date asc;\n", nativeQuery = true)
    List<WeekSInvoice> getWeekSInvoices();

    @Query(value = "select SUM(total) as total_price from invoices WHERE DATE(created_at) = CURDATE()", nativeQuery = true)
    Double getTotalGainedToday();

}
