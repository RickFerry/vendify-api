package com.vendify.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vendify.model.Sale;
import com.vendify.service.SaleService;
import com.vendify.service.SmsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;
    private final SmsService smsService;

    @GetMapping
    public ResponseEntity<Page<Sale>> list(@RequestParam(defaultValue = "") String minDate,
                                           @RequestParam(defaultValue = "") String maxDate,
                                           Pageable pageable) {    	
        return ResponseEntity.ok(saleService.list(minDate, maxDate, pageable));
    }

    @GetMapping("/{id}/send-sms")
    public ResponseEntity<Void> sendSms(@PathVariable Long id) {
        smsService.sendSms(id);
        return ResponseEntity.ok().build();
    }
}
