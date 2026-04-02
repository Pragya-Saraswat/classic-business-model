package com.businessmodel.service.impl;

import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.OrderWithDetailsDto;
import com.businessmodel.entity.Order;
import com.businessmodel.exception.BadRequestException;
import com.businessmodel.exception.ResourceNotFoundException;
import com.businessmodel.mapper.OrderMapper;
import com.businessmodel.repository.OrderRepo;
import com.businessmodel.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepo orderRepo;

	// ----------- Orders by Status ---------
	@Override
	public Page<OrderDto> getOrdersByStatus(String status, int page, int size) {

		if (status == null || status.isBlank()) {
			throw new BadRequestException("Status cannot be null or empty");
		}

		if (page < 0 || size <= 0) {
			throw new BadRequestException("Invalid pagination parameters");
		}

		Pageable pageable = PageRequest.of(page, size);

		Page<Order> orders = orderRepo.findByStatus(status, pageable);

		if (orders.isEmpty()) {
			throw new ResourceNotFoundException("No orders found with status: " + status);
		}

		return orders.map(OrderMapper::toOrderDto);
	}

	// ----------- Orders by Customer ----------
	@Override
	public Page<OrderDto> getOrdersByCustomer(Integer customerId, int page, int size) {

		if (customerId == null) {
			throw new BadRequestException("Customer ID cannot be null");
		}

		if (page < 0 || size <= 0) {
			throw new BadRequestException("Invalid pagination parameters");
		}

		Pageable pageable = PageRequest.of(page, size);

		Page<Order> orders = orderRepo.findByCustomerCustomerNumber(customerId, pageable);

		if (orders.isEmpty()) {
			throw new ResourceNotFoundException("No orders found for customer id: " + customerId);
		}

		return orders.map(OrderMapper::toOrderDto);
	}

	// ------------ Orders by Customer + Status ------------
	@Override
	public Page<OrderDto> getOrdersByCustomerIdAndStatus(Integer customerId, String status, int page, int size) {

		if (customerId == null) {
			throw new BadRequestException("Customer ID cannot be null");
		}

		if (status == null || status.isBlank()) {
			throw new BadRequestException("Status cannot be empty");
		}

		if (page < 0 || size <= 0) {
			throw new BadRequestException("Invalid pagination parameters");
		}

		Pageable pageable = PageRequest.of(page, size);

		Page<Order> orders = orderRepo.findByCustomerCustomerNumberAndStatus(customerId, status, pageable);

		if (orders.isEmpty()) {
			throw new ResourceNotFoundException(
					"No orders found for customer id: " + customerId + " with status: " + status);
		}

		return orders.map(OrderMapper::toOrderDto);
	}

	// ------------ Order with Details ----------
	@Override
	public OrderWithDetailsDto getOrderWithDetails(Integer orderId) {

		if (orderId == null) {
			throw new BadRequestException("Order ID cannot be null");
		}

		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

		return OrderMapper.toOrderWithDetailsDto(order);
	}
}