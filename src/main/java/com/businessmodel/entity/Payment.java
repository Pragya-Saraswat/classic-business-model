package com.businessmodel.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    private String checkNumber;

    // Foreign Key Mapping
    @ManyToOne
    @JoinColumn(name = "customerNumber", nullable = false)
    private Customer customer;
    private LocalDate paymentDate;
    private double amount;

    public Payment() {
    }

    public Payment(double amount, String checkNumber, Customer customer, LocalDate paymentDate) {
        this.amount = amount;
        this.checkNumber = checkNumber;
        this.customer = customer;
        this.paymentDate = paymentDate;
    }
    // Getters and Setters

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}