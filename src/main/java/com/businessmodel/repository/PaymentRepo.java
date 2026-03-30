package com.businessmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.businessmodel.entity.Payment;
import com.businessmodel.entity.PaymentId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PaymentRepo  extends JpaRepository<Payment, PaymentId> {
}
