package com.vendify.repository;

import com.vendify.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s WHERE s.date BETWEEN :min AND :max ORDER BY s.date DESC")
    Page<Sale> findAllByDate(@Param("min") LocalDate min, @Param("max") LocalDate max, Pageable pageable);
}