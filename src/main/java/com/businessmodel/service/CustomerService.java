package com.businessmodel.service;

import com.businessmodel.dto.CustomerDto;
import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.AmountDto;
import com.businessmodel.dto.SupportDto;

import java.util.List;

public interface CustomerService {
	
	List<CustomerDto> getCustomersByCountry(String country);
	List<CustomerDto> getTopCustomers();
	List<OrderDto> getOrdersByCustomer(Integer customerId);
	List<OrderDto> getOrdersByCustomerIdAndStatus(Integer customerId, String status);
	AmountDto getTotalPaymentAmount(Integer customerId);
	SupportDto getCustomerSupport(Integer customerId);
}
