package com.businessmodel.service;

import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.SupportDto;
import com.businessmodel.entity.Customer;
import com.businessmodel.entity.Employee;
import com.businessmodel.entity.Order;
import com.businessmodel.exception.ResourceNotFoundException;
import com.businessmodel.repository.CustomerRepo;
import com.businessmodel.repository.OrderRepo;
import com.businessmodel.service.impl.CustomerServiceImpl;
import com.businessmodel.dto.CustomerDto;
import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.SupportDto;
import com.businessmodel.entity.Customer;
import com.businessmodel.entity.Employee;
import com.businessmodel.entity.Order;
import com.businessmodel.exception.BadRequestException;
import com.businessmodel.exception.ResourceNotFoundException;
import com.businessmodel.repository.CustomerRepo;
import com.businessmodel.repository.OrderRepo;
import com.businessmodel.service.impl.CustomerServiceImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class CustomerServiceImplTest {

    @Mock
    private CustomerRepo customerRepo;
    @Mock
    private OrderRepo orderRepo;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void testGetOrdersByCustomerIdAndStatusSuccess(){
        final Customer customer = new Customer();
        customer.setCustomerName("Test");

        final Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDetails(new ArrayList<>());

        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(orderRepo.findByCustomerCustomerNumberAndStatus(1,"Shipped"))
                .thenReturn(List.of(order));

        final List<OrderDto> result =
                customerService.getOrdersByCustomerIdAndStatus(1,"Shipped");
        assertEquals(1,result.size());
    }
    @Test
    void testGetOrdersByCustomerIdAndStatusFail(){
        assertThrows(ResourceNotFoundException.class,
                ()->customerService.getOrdersByCustomerIdAndStatus(1,""));
    }


    @Test
    void testGetCustomerSupportSuccess(){
        final Customer customer=new Customer();
        customer.setSalesRep(new Employee());
        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        final SupportDto result=customerService.getCustomerSupport(1);
        assertNotNull(result);
    }
    @Test
    void testGetCustomerSupportFail(){
        when(customerRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                ()->customerService.getCustomerSupport(1));
    }

    @Test
    void testGetOrdersByCustomerSuccess() {
        final Customer customer = new Customer();
        customer.setCustomerNumber(1);
        customer.setCustomerName("Test");

        final Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDetails(new ArrayList<>());

        final Page<Order> orderPage = new PageImpl<>(List.of(order));

        when(customerRepo.findById(1)).thenReturn(Optional.of(customer));
        when(orderRepo.findByCustomer(any(), any())).thenReturn(orderPage);

        final Page<OrderDto> result = customerService.getOrdersByCustomer(1,0,10);
        assertNotNull(result);
    }
    @Test
    void testGetOrdersByCustomerFail(){
        when(customerRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                ()->customerService.getOrdersByCustomer(1,0,10));
    }
}
