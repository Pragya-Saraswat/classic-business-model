package com.businessmodel.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.businessmodel.entity.Customer;
import com.businessmodel.exception.BadRequestException;
import com.businessmodel.exception.BusinessException;
import com.businessmodel.exception.ResourceNotFoundException;
import com.businessmodel.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.businessmodel.dto.AmountDto;
import com.businessmodel.entity.Payment;
import com.businessmodel.mapper.AmountMapper;
import com.businessmodel.repository.PaymentRepo;
import com.businessmodel.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public AmountDto getTotalRevenue() {
        List<Payment> allPayments = paymentRepo.findAll();
        if (allPayments.isEmpty()) {
            throw new BusinessException("No payment records found");
        }
        BigDecimal totalRevenue = BigDecimal.ZERO;
        for (Payment payment : allPayments) {
            if (payment.getAmount() != null) {
                totalRevenue = totalRevenue.add(payment.getAmount());
            }
        }
        return AmountMapper.toRevenueDTO(totalRevenue);

    }

    @Override
    public AmountDto getTotalPaymentAmount(Integer customerId) {
        if (customerId == null || customerId <= 0) {
            throw new BadRequestException("Invalid customer ID: " + customerId);
        }

        customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

        List<Payment> payments = paymentRepo.findByCustomerCustomerNumber(customerId);

        if (payments.isEmpty()) {
            throw new BusinessException("No payments found for customer id: " + customerId);
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