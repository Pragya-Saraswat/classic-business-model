package com.businessmodel.service.impl;

import com.businessmodel.dto.*;
import com.businessmodel.entity.*;
import com.businessmodel.repository.*;
import com.businessmodel.service.IOrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final OrderDetailRepo orderDetailRepo;

    // 🔹 1. Orders by status
    @Override
    public List<OrderDto> getOrdersByStatus(String status) {
        return orderRepo.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 🔹 2. Orders by customer
    @Override
    public List<OrderDto> getOrdersByCustomer(int customerId) {

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return orderRepo.findByCustomer(customer)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 🔹 3. Orders by customer + status
    @Override
    public List<OrderDto> getOrdersByCustomerAndStatus(int customerId, String status) {

        return orderRepo.findByCustomer_CustomerNumberAndStatus(customerId, status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 🔥 4. Order with details
    @Override
    public OrderWithDetailsDto getOrderWithDetails(int orderId) {

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderDetailDto> details = orderDetailRepo
                .findByOrder_OrderNumber(orderId)
                .stream()
                .map(detail -> OrderDetailDto.builder()
                        .productCode(detail.getProduct().getProductCode())
                        .productName(detail.getProduct().getProductName())
                        .quantityOrdered(detail.getQuantityOrdered())
                        .priceEach(detail.getPriceEach())
                        .build())
                .collect(Collectors.toList());

        return OrderWithDetailsDto.builder()
                .orderNumber(order.getOrderNumber())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .customerName(order.getCustomer().getCustomerName())
                .orderDetails(details)
                .build();
    }

    // 🔧 Helper
    private OrderDto convertToDTO(Order order) {

        return OrderDto.builder()
                .orderNumber(order.getOrderNumber())
                .orderDate(order.getOrderDate())
                .requiredDate(order.getRequiredDate())
                .shippedDate(order.getShippedDate())
                .status(order.getStatus())
                .customerName(order.getCustomer().getCustomerName())
                .build();
    }
}