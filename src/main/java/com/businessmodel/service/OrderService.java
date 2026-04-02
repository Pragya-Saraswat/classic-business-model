package com.businessmodel.service;

import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.OrderWithDetailsDto;
import org.springframework.data.domain.Page;

public interface OrderService {
	Page<OrderDto> getOrdersByStatus(String status, int page, int size);
	Page<OrderDto> getOrdersByCustomer(Integer customerId, int page, int size);
    Page<OrderDto> getOrdersByCustomerIdAndStatus(Integer customerId, String status, int page, int size);
    OrderWithDetailsDto getOrderWithDetails(Integer orderId);
}