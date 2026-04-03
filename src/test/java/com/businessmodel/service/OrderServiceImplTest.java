package com.businessmodel.service;

import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.OrderWithDetailsDto;
import com.businessmodel.entity.Customer;
import com.businessmodel.entity.Order;
import com.businessmodel.exception.BadRequestException;
import com.businessmodel.exception.ResourceNotFoundException;
import com.businessmodel.repository.OrderRepo;
import com.businessmodel.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class OrderServiceImplTest {

    @Mock
    private OrderRepo orderRepo;
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void testGetOrderWithDetailsSuccess() {

        final Customer customer = new Customer();
        customer.setCustomerName("Test");

        final Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDetails(new ArrayList<>());

        when(orderRepo.findById(1)).thenReturn(Optional.of(order));
        final OrderWithDetailsDto result =
                orderService.getOrderWithDetails(1);
        assertNotNull(result);
    }
    @Test
    void testGetOrderWithDetailsNotFound() {
        when(orderRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> orderService.getOrderWithDetails(1));
    }

    @Test
    void testGetOrdersByStatusSuccess() {
        final Customer customer = new Customer();
        customer.setCustomerName("Test");

        final Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDetails(new ArrayList<>());

        when(orderRepo.findByStatus(eq("Shipped"), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(order)));
        final Page<OrderDto> result =
                orderService.getOrdersByStatus("Shipped",0,10);
        assertFalse(result.isEmpty());
    }
    @Test
    void testGetOrdersByStatusInvalid() {
        assertThrows(BadRequestException.class,
                () -> orderService.getOrdersByStatus("",0,10));
    }

}
