package com.vendify.service;

import com.vendify.model.Sale;
import com.vendify.repository.SaleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;

    @Transactional(readOnly = true)
    public Page<Sale> list(String minDate, String maxDate, Pageable pageable) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate minDateParsed = minDate.isEmpty() ? today.minusDays(365) : LocalDate.parse(minDate, formatter);
        LocalDate maxDateParsed = maxDate.isEmpty() ? today : LocalDate.parse(maxDate, formatter);

        log.info("minDateParsed: {}", minDateParsed);
        log.info("maxDateParsed: {}", maxDateParsed);

        Page<Sale> sales = saleRepository.findAllByDate(minDateParsed, maxDateParsed, pageable);
        log.info("Number of sales found: {}", sales.getTotalElements());
        return sales;
    }
}
