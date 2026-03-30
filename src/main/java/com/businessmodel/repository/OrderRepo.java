package com.businessmodel.repository;

import com.businessmodel.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.businessmodel.entity.Order;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
