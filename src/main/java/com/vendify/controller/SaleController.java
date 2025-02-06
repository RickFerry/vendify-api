package com.vendify.controller;

import com.vendify.model.Sale;
import com.vendify.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;

    @GetMapping
    public ResponseEntity<Page<Sale>> list(@RequestParam(value = "minDate", defaultValue = "") String minDate,
                                           @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
                                           Pageable pageable) {
        return ResponseEntity.ok(saleService.list(minDate, maxDate, pageable));
    }
}
