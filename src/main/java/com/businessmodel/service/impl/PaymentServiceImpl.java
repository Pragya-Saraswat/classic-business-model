package com.businessmodel.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.businessmodel.dto.AmountDto;
import com.businessmodel.entity.Payment;
import com.businessmodel.mapper.PaymentMapper;
import com.businessmodel.repository.CustomerRepo;
import com.businessmodel.repository.PaymentRepo;
import com.businessmodel.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepo prepo;
	@Autowired
	private CustomerRepo crepo;
	@Autowired
	private PaymentMapper paymap;

	public PaymentServiceImpl(PaymentRepo paymentRepository, CustomerRepo customerRepository,
			PaymentMapper paymentMapper) {
		this.prepo = prepo;
		this.crepo = crepo;
		this.paymap = paymap;
	}

	@Override
	public AmountDto getTotalRevenue() {
	

		    List<Payment> allPayments = prepo.findAll();

		    BigDecimal totalRevenue = BigDecimal.ZERO;
		    for (Payment payment : allPayments) {
		        totalRevenue = totalRevenue.add(payment.getAmount());
		    }

			return paymap.toRevenueDto(totalRevenue);  
		}
	}


