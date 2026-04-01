package com.businessmodel.repository;

import com.businessmodel.entity.Order;
import com.businessmodel.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findByStatus(String status);
    List<Order> findByCustomer(Customer customer);
    List<Order> findByCustomer_CustomerNumberAndStatus(Integer customerNumber, String status);
    List<Order> findByCustomer_CustomerNumber(Integer customerNumber);
}