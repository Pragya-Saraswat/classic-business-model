package com.businessmodel.service.impl;

import com.businessmodel.dto.*;
import com.businessmodel.entity.Customer;
import com.businessmodel.entity.Employee;
import com.businessmodel.exception.BadRequestException;
import com.businessmodel.exception.ResourceNotFoundException;
import com.businessmodel.mapper.CustomerMapper;
import com.businessmodel.mapper.SupportMapper;
import com.businessmodel.repository.CustomerRepo;
import com.businessmodel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	// --------- Customers by Country ----------
	@Override
	public Page<CustomerDto> getCustomersByCountry(String country, int page, int size) {
		if (country == null || country.isBlank()) {
			throw new BadRequestException("Country cannot be empty");
		}
		if (page < 0 || size <= 0) {
			throw new BadRequestException("Invalid pagination parameters");
		}
		Pageable pageable = PageRequest.of(page, size);

		Page<Customer> customerPage = customerRepo.findByCountry(country, pageable);
		if (customerPage.isEmpty()) {
			throw new ResourceNotFoundException("No customers found for country: " + country);
		}
		return customerPage.map(CustomerMapper::toCustomerDto);
	}

	// --------- Top Customers ----------
	@Override
	public Page<CustomerDto> getTopCustomers(int page, int size) {

		if (page < 0 || size <= 0) {
			throw new BadRequestException("Invalid pagination parameters");
		}

		Pageable pageable = PageRequest.of(page, size);

		Page<Customer> customers = customerRepo.findAllByOrderByCreditLimitDesc(pageable);

		if (customers.isEmpty()) {
			throw new ResourceNotFoundException("No top customers found");
		}

		return customers.map(CustomerMapper::toCustomerDto);
	}

	// ---------- Customer Support --------

	 @Override
	    public SupportDto getCustomerSupport(Integer customerId) {

	        if (customerId == null) {
	            throw new BadRequestException("Customer ID cannot be null");
	        }

	        Customer customer = customerRepo.findById(customerId)
	                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

	        Employee employee = customer.getSalesRep();

	        if (employee == null) {
	            throw new ResourceNotFoundException("No support assigned to customer");
	        }

	        return SupportMapper.toSupportDto(employee);
	    }
}
