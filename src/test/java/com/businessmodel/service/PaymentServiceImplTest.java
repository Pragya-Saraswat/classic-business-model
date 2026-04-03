package com.businessmodel.service;

import com.businessmodel.dto.AmountDto;
import com.businessmodel.entity.Customer;
import com.businessmodel.exception.BusinessException;
import com.businessmodel.exception.ResourceNotFoundException;
import com.businessmodel.repository.CustomerRepo;
import com.businessmodel.repository.PaymentRepo;
import com.businessmodel.service.impl.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class PaymentServiceImplTest {
	@Mock
	private PaymentRepo paymentRepo;
	@Mock
	private CustomerRepo customerRepo;
	@InjectMocks
	private PaymentServiceImpl paymentService;

	@Test
	void testGetTotalPaymentAmountSuccess() {
		when(customerRepo.findById(1)).thenReturn(Optional.of(new Customer()));
		when(paymentRepo.sumPaymentByCustomer(1)).thenReturn(new BigDecimal("500"));
		final AmountDto result = paymentService.getTotalPaymentAmount(1);
		assertNotNull(result);
	}

	@Test
	void testGetTotalPaymentAmountInvalidCustomer() {
		when(customerRepo.findById(1)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> paymentService.getTotalPaymentAmount(1));
	}

	@Test
	void testGetYearlyRevenueSuccess() {
		when(paymentRepo.getYearlyRevenue()).thenReturn(List.of(new AmountDto()));
		final List<AmountDto> result = paymentService.getYearlyRevenue();
		assertNotNull(result);
	}

	@Test
	void testGetYearlyRevenueNull() {
		when(paymentRepo.getYearlyRevenue()).thenReturn(null);
		assertThrows(BusinessException.class, () -> paymentService.getYearlyRevenue());
	}

}
