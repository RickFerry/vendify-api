package com.vendify.controller;

import com.vendify.model.Sale;
import com.vendify.service.SaleService;
import com.vendify.service.SmsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;
    private final SmsService smsService;

    @GetMapping
    public ResponseEntity<Page<Sale>> list(@RequestParam(value = "minDate", defaultValue = "") String minDate,
                                           @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
                                           Pageable pageable) {
        log.info("Received minDate: {}", minDate);
        log.info("Received maxDate: {}", maxDate);
        return ResponseEntity.ok(saleService.list(minDate, maxDate, pageable));
    }

    @GetMapping("/{id}/send-sms")
    public ResponseEntity<Void> sendSms(@PathVariable Long id) {
        smsService.sendSms(id);
        return ResponseEntity.ok().build();
    }
}
