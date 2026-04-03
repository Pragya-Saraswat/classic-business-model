package com.businessmodel.mapper;

import java.math.BigDecimal;

import com.businessmodel.dto.AmountDto;
import com.businessmodel.entity.Customer;
import com.businessmodel.entity.Payment;
import com.businessmodel.entity.PaymentId;

public class AmountMapper {

	public static AmountDto toYearlyDTO(final Integer year, final BigDecimal revenue) {
		return new AmountDto(year, revenue);
	}

	public static AmountDto toCustomerSpendingDto(final Integer customerNumber, final BigDecimal totalAmount) {
		final AmountDto dto = new AmountDto();
		dto.setCustomerNumber(customerNumber);
		dto.setTotalAmount(totalAmount);

		return dto;
	}

	public static Payment toEntity(final AmountDto dto) {
		final PaymentId paymentId = new PaymentId();
		paymentId.setCustomerNumber(dto.getCustomerNumber());
		paymentId.setCheckNumber(dto.getCheckNumber());

		final Payment payment = new Payment();
		payment.setPaymentId(paymentId);
		payment.setPaymentDate(dto.getPaymentDate());
		payment.setAmount(dto.getAmount());

		final Customer customer = new Customer();
		customer.setCustomerNumber(dto.getCustomerNumber());

		payment.setCustomer(customer);

		return payment;
	}

	public static AmountDto toDTO(final Payment payment) {

		final AmountDto dto = new AmountDto();

		dto.setCustomerNumber(payment.getPaymentId().getCustomerNumber());
		dto.setCheckNumber(payment.getPaymentId().getCheckNumber());
		dto.setPaymentDate(payment.getPaymentDate());
		dto.setAmount(payment.getAmount());

		return dto;
	}
}