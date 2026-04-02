package com.businessmodel.service.impl;

import com.businessmodel.dto.*;
import com.businessmodel.entity.*;
import com.businessmodel.exception.BadRequestException;
import com.businessmodel.exception.ResourceNotFoundException;
import com.businessmodel.mapper.OrderMapper;
import com.businessmodel.repository.*;
import com.businessmodel.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final OrderDetailRepo orderDetailRepo;

    @Override
    public List<OrderDto> getOrdersByCustomer(Integer customerId) {
        if(customerId==null){
            throw new BadRequestException("Customer Id cannot be null");
        }

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not find with id: " + customerId));
        List<Order> order = orderRepo.findByCustomer(customer);
        List<OrderDto> orderDto= new ArrayList<>();
        order.forEach(o -> orderDto.add(OrderMapper.toOrderDto(o)));
        return orderDto;
    }

    @Override
    public List<OrderDto> getOrdersByStatus(String status) {
        if(status==null || status.isBlank()){
            throw new BadRequestException("Status cannot be null or empty");
        }
        List<Order> orders = orderRepo.findByStatus(status);
        List<OrderDto> orderDto = new ArrayList<>();
        orders.forEach(order -> orderDto.add(OrderMapper.toOrderDto(order)));
        return orderDto;
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Integer customerId) {

        Customer customer= customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        List<Order> orders = orderRepo.findByCustomer(customer);
        List<OrderDto> orderDto = new ArrayList<>();
        orders.forEach(order -> orderDto.add(OrderMapper.toOrderDto(order)));
        return orderDto;
    }

    @Override
    public OrderWithDetailsDto getOrderWithDetails(Integer orderId) {
        Order order=orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not find with id: " + orderId));
        return OrderMapper.toOrderWithDetailsDto(order);
    }

}