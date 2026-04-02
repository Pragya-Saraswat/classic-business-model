package com.businessmodel.controller;

import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.OrderWithDetailsDto;
import com.businessmodel.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	// -------- Get order with details ------
	@GetMapping("/{id}")
	public OrderWithDetailsDto getOrderWithDetails(@PathVariable Integer id) {
		return orderService.getOrderWithDetails(id);
	}

	// ------- Get orders by status ------
	@GetMapping
	public Page<OrderDto> getOrdersByStatus(@RequestParam String status, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		return orderService.getOrdersByStatus(status, page, size);
	}
}