package com.vendify.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendify.model.Sale;
import com.vendify.repository.SaleRepository;

import lombok.AllArgsConstructor;

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

        return saleRepository.findAllByDate(minDateParsed, maxDateParsed, pageable);
    }
}
