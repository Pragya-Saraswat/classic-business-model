package com.businessmodel.repository;

import com.businessmodel.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Page<Order> findByStatus(String status, Pageable pageable);
    Page<Order> findByCustomerCustomerNumber(Integer customerNumber, Pageable pageable);
    Page<Order> findByCustomerCustomerNumberAndStatus(Integer customerNumber, String status, Pageable pageable);
}