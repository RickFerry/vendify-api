package com.vendify.service;

import com.vendify.model.Sale;
import com.vendify.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;

    @Transactional(readOnly = true)
    public Page<Sale> list(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }
}
