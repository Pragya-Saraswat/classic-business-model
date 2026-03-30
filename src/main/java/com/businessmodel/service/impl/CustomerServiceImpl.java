package com.businessmodel.service.impl;

import com.businessmodel.dto.*;
import com.businessmodel.entity.Customer;
import com.businessmodel.entity.Employee;
import com.businessmodel.entity.Order;
import com.businessmodel.mapper.CustomerEntityMapper;
import com.businessmodel.mapper.OrderEntityMapper;
import com.businessmodel.repository.CustomerRepo;
import com.businessmodel.repository.OrderRepo;
import com.businessmodel.repository.PaymentRepo;
import com.businessmodel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private PaymentRepo paymentRepo;

    @Override
    public List<CustomerDto> getCustomersByCountry(String country) {
        List<Customer> customer=customerRepo.findByCountry(country);
        List<CustomerDto> customerDto=new ArrayList<>();
        customer.forEach(c->customerDto.add(CustomerEntityMapper.toCustomerDto(c)));
        return customerDto;
    }

    @Override
    public List<CustomerDto> getTopCustomers() {
        List<Customer> customer=customerRepo.findTop10ByOrderByCreditLimitDesc();
        List<CustomerDto> customerDto=new ArrayList<>();
        customer.forEach(c->customerDto.add(CustomerEntityMapper.toCustomerDto(c)));
        return customerDto;
    }

    @Override
    public List<OrderDto> getOrdersByCustomer(Integer customerId) {
        List<Order> orders=orderRepo.findByCustomerCustomerNumber(customerId);
        List<OrderDto> orderDto=new ArrayList<>();
        orders.forEach(o-> OrderEntityMapper.toOrderDto(o));
        return orderDto;
    }

    @Override
    public List<OrderDto> getOrdersByCustomerIdAndStatus(Integer customerId, String status) {
        List<Order> orders=orderRepo.findByCustomerCustomerNumberAndStatus(customerId, status);
        List<OrderDto> orderDto=new ArrayList<>();
        orders.forEach(o->OrderEntityMapper.toOrderDto(o));
        return orderDto;
    }

    @Override
    public AmountDto getTotalPaymentAmount(Integer customerId) {
        Double total=paymentRepo.sumPaymentByCustomer(customerId);
        return AmountEntityMapper.toAmountDto(total);
    }

    @Override
    public SupportDto getCustomerSupport(Integer customerId) {
        Customer customer=customerRepo.findById(customerId).get();
        Employee emp=customer.getSalesRep();
        return SupportEntityMapper.toSupportDto(emp);
    }
}


