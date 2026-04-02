package com.businessmodel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.businessmodel.dto.AmountDto;
import com.businessmodel.service.PaymentService;

@RestController
@RequestMapping("api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/revenue")
    public ResponseEntity<AmountDto> getTotalRevenue() {
        return new ResponseEntity<>(paymentService.getTotalRevenue(), HttpStatus.OK);
    }
}