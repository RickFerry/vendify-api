package com.vendify.service;

import com.vendify.model.Sale;
import com.vendify.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;

    @Transactional(readOnly = true)
    public Page<Sale> list(String minDate, String maxDate, Pageable pageable) {
        LocalDate today = LocalDate.now();

        LocalDate minDateParsed = minDate.isEmpty() ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate maxDateParsed = maxDate.isEmpty() ? today : LocalDate.parse(maxDate);

        return saleRepository.findAll(minDateParsed, maxDateParsed, pageable);
    }
}
