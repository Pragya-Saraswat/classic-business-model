package com.businessmodel.controller;

import com.businessmodel.dto.AmountDto;
import com.businessmodel.service.PaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // ----------- Total Revenue -----------
    @GetMapping("/revenue")
    public AmountDto getTotalRevenue() {
        return paymentService.getTotalRevenue();
    }
}