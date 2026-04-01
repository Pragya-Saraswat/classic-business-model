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
    public ResponseEntity<?> getOrderWithDetails(@PathVariable Integer id) {
        try {
            OrderWithDetailsDto order = orderService.getOrderWithDetails(id);
            return ResponseEntity.ok(order);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getOrdersByStatus(@RequestParam(required = false) String status) {
        try {
            if (status == null) {
                return ResponseEntity.badRequest()
                        .body("Please provide status parameter");
            }

            List<OrderDto> orders = orderService.getOrdersByStatus(status);
            return ResponseEntity.ok(orders);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrdersByCustomer(@PathVariable Integer customerId) {
        try {
            List<OrderDto> orders = orderService.getOrdersByCustomerId(customerId);
            return ResponseEntity.ok(orders);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/customer/{customerId}/page")
    public ResponseEntity<?> getOrdersByCustomerWithPagination(
            @PathVariable Integer customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        try {
            Page<OrderDto> orders = orderService.getOrdersByCustomer(customerId, page, size);
            return ResponseEntity.ok(orders);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}