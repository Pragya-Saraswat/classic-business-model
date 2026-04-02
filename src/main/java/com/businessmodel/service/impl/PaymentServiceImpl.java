package com.businessmodel.service.impl;

import com.businessmodel.dto.AmountDto;
import com.businessmodel.entity.Payment;
import com.businessmodel.exception.BadRequestException;
import com.businessmodel.exception.ResourceNotFoundException;
import com.businessmodel.mapper.AmountMapper;
import com.businessmodel.repository.PaymentRepo;
import com.businessmodel.service.PaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;

    // -------- Total Revenue --------
    @Override
    public AmountDto getTotalRevenue() {

        List<Payment> payments = paymentRepo.findAll();

        if (payments.isEmpty()) {
            throw new ResourceNotFoundException("No payments found");
        }

        BigDecimal total = BigDecimal.ZERO;

        for (Payment payment : payments) {
            if (payment.getAmount() != null) {
                total = total.add(payment.getAmount());
            }
        }

        return AmountMapper.toRevenueDTO(total);
    }

    // -------- Customer Total spending --------
    @Override
    public AmountDto getTotalPaymentAmount(Integer customerId) {

        if (customerId == null) {
            throw new BadRequestException("Customer ID cannot be null");
        }

        List<Payment> payments = paymentRepo.findByCustomerCustomerNumber(customerId);

        if (payments.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No payments found for customer id: " + customerId);
        }

        BigDecimal total = BigDecimal.ZERO;

        for (Payment payment : payments) {
            if (payment.getAmount() != null) {
                total = total.add(payment.getAmount());
            }
        }

        return AmountMapper.toCustomerSpendingDto(customerId, total);
    }
}