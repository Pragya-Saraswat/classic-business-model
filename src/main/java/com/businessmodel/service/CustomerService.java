package com.businessmodel.service;

import com.businessmodel.dto.CustomerDto;
import com.businessmodel.dto.SupportDto;

import org.springframework.data.domain.Page;

public interface CustomerService {

	Page<CustomerDto> getCustomersByCountry(String country, int page, int size);
	Page<CustomerDto> getTopCustomers(int page, int size);
	SupportDto getCustomerSupport(Integer customerId);
}
