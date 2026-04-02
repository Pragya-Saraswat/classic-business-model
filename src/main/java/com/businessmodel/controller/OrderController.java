package com.businessmodel.controller;

import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.OrderWithDetailsDto;
import com.businessmodel.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderWithDetailsDto getOrderWithDetails(@PathVariable Integer id) {
        return orderService.getOrderWithDetails(id);
    }

    @GetMapping
    public ResponseEntity<?> getOrdersByStatus(@RequestParam(required = false) String status) {
        return  new ResponseEntity<>(orderService.getOrdersByStatus(status), HttpStatus.OK);
    }

}