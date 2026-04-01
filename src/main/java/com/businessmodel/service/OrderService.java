package com.businessmodel.service;

import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.OrderWithDetailsDto;

import java.util.List;
import org.springframework.data.domain.Page;

public interface OrderService {
    List<OrderDto> getOrdersByStatus(String status);
    List<OrderDto> getOrdersByCustomerId(Integer customerId);
    OrderWithDetailsDto getOrderWithDetails(Integer orderId);
    Page<OrderDto> getOrdersByCustomer(Integer customerId, int page, int size);
}