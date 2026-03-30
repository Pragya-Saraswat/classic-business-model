package com.businessmodel.mapper;

import com.businessmodel.dto.CustomerDto;
import com.businessmodel.entity.Customer;

public class CustomerEntityMapper {

    public static Customer toCustomerEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setCustomerNumber(dto.getCustomerNumber());
        customer.setCustomerName(dto.getCustomerName());
        customer.setCountry(dto.getCountry());
        customer.setPhone(dto.getPhone());
        customer.setCreditLimit(dto.getCreditLimit());
        return customer;
    }

    public static CustomerDto toCustomerDto(Customer c) {
        return new CustomerDto(
                c.getCustomerNumber(),
                c.getCustomerName(),
                c.getCountry(),
                c.getPhone(),
                c.getCreditLimit()
        );
    }
}
