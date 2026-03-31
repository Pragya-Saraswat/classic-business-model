package com.businessmodel.service;

import com.businessmodel.dto.CustomerDto;
import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.AmountDto;
import com.businessmodel.dto.SupportDto;

import java.util.List;

import org.springframework.data.domain.Page;

public interface CustomerService {
	
	Page<CustomerDto> getCustomersByCountry(String country, int page, int size);
	List<CustomerDto> getTopCustomers(int page, int size);
	List<OrderDto> getOrdersByCustomer(Integer customerId);
	List<OrderDto> getOrdersByCustomerIdAndStatus(Integer customerId, String status);
	AmountDto getTotalPaymentAmount(Integer customerId);
	SupportDto getCustomerSupport(Integer customerId);
}
