package com.businessmodel.service.impl;

import com.businessmodel.dto.CustomerDto;
import com.businessmodel.service.ICustomerService;

import java.util.List;

public class CustomerServiceImpl implements ICustomerService {
    @Override
    public List<CustomerDto> getCustomersByCountry(String country) {
        return List.of();
    }

    @Override
    public List<CustomerDto> getTopCustomers() {
        return List.of();
    }

    @Override
    public List<OrderDto> getOrdersByCustomer(Integer customerId) {
        return List.of();
    }

    @Override
    public List<OrderDto> getOrderByCustomerIdAndStatus(Integer customerId, String status) {
        return List.of();
    }

    @Override
    public AmountDto getTotalPaymentAmount(Integer customerId) {
        return null;
    }

    @Override
    public SupportDTO getCustomerSupport(Integer customerId) {
        return null;
    }
}
