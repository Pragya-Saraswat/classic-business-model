package com.businessmodel.controller;

import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.SupportDto;
import com.businessmodel.service.OrderService;
import com.businessmodel.service.PaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import com.businessmodel.dto.AmountDto;
import com.businessmodel.dto.CustomerDto;
import com.businessmodel.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;
	private final PaymentService paymentService;
	private final OrderService orderService;

	// ------------- Customers By Country -------
	@GetMapping
	public Page<CustomerDto> getCustomersByCountry(@RequestParam String country,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		return customerService.getCustomersByCountry(country, page, size);
	}

	// -------------- Top Customers  ----------
	@GetMapping("/top")
	public Page<CustomerDto> getTopCustomers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		return customerService.getTopCustomers(page, size);
	}

	// -------------- Orders of customer ----------
	@GetMapping("/{id}/orders")
	public Page<OrderDto> getOrdersByCustomer(@PathVariable Integer id, @RequestParam(required = false) String status,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

		if (status != null) {
			return orderService.getOrdersByCustomerIdAndStatus(id, status, page, size);
		}

		return orderService.getOrdersByCustomer(id, page, size);
	}

	// ------------ Customer support ---------
	@GetMapping("/{id}/support")
	public SupportDto getCustomerSupport(@PathVariable Integer id) {
		return customerService.getCustomerSupport(id);
	}

	// ------------ Customer Spending -------
	@GetMapping("/{id}/payment/amount")
	public AmountDto getCustomerSpending(@PathVariable Integer id) {
		return paymentService.getTotalPaymentAmount(id);
	}
}