package com.businessmodel.dto;

import com.businessmodel.entity.Customer;

public class EntityMapper {
//    ========================customer====================

    public static Customer convertObjectToEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setCustomerNumber(dto.getCustomerNumber());
        customer.setCustomerName(dto.getCustomerName());
        customer.setCountry(dto.getCountry());
        customer.setPhone(dto.getPhone());
        customer.setCreditLimit(dto.getCreditLimit());
        return customer;
    }

    public static CustomerDto convertEntityToDTO(Customer c) {
        return new CustomerDto(c.getCustomerNumber(),c.getCustomerName(),c.getCountry(),c.getPhone(),c.getCreditLimit());
    }
}
