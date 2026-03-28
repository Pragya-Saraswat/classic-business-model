package com.businessmodel.service;

import com.businessmodel.dto.CustomerDto;
import com.businessmodel.dto.OrderDto;

import java.util.List;

public interface ICustomerService {
    public List<CustomerDto> getCustomersByCountry(String country);
    public List<CustomerDto> getTopCustomers();
    public List<OrderDto> getOrdersByCustomer(Integer customerId);
    public List<OrderDto> getOrderByCustomerIdAndStatus(Integer customerId, String status);
    public AmountDto getTotalPaymentAmount(Integer customerId);
    public SupportDTO getCustomerSupport(Integer customerId);
}
