package com.businessmodel.service;

import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.OrderWithDetailsDto;

import java.util.List;

public interface IOrderService {

    List<OrderDto> getOrdersByStatus(String status);

    List<OrderDto> getOrdersByCustomer(int customerId);

    List<OrderDto> getOrdersByCustomerAndStatus(int customerId, String status);

    OrderWithDetailsDto getOrderWithDetails(int orderId);
}