package com.businessmodel.controller;

import com.businessmodel.dto.OrderDto;
import com.businessmodel.dto.SupportDto;
import com.businessmodel.service.OrderService;
import com.businessmodel.service.PaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import com.businessmodel.dto.AmountDto;
import com.businessmodel.dto.CustomerDto;
import com.businessmodel.service.CustomerService;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final PaymentService paymentService;

    private final OrderService orderService;

    @GetMapping
    public Page<CustomerDto> getCustomersByCountry(@RequestParam String country,
                                                   @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return customerService.getCustomersByCountry(country, page, size);
    }

    @GetMapping("/top")
    public List<CustomerDto> getTopCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return customerService.getTopCustomers(page, size);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@PathVariable Integer id) {
        return new ResponseEntity<>(orderService.getOrdersByCustomer(id), HttpStatus.OK);
    }


    @GetMapping("/{id}/orders_status")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerIdAndStatus(@PathVariable Integer id, @RequestParam String status) {
        return new ResponseEntity<>(customerService.getOrdersByCustomerIdAndStatus(id, status), HttpStatus.OK);
    }

    @GetMapping("/{id}/support")
    public ResponseEntity<SupportDto> getCustomerSupport(@PathVariable Integer id) {
        return new ResponseEntity<>(customerService.getCustomerSupport(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/payment/amount")
    public ResponseEntity<AmountDto> getCustomerSpending(@PathVariable Integer id) {
        return new ResponseEntity<AmountDto>(paymentService.getTotalPaymentAmount(id), HttpStatus.OK);
    }
}