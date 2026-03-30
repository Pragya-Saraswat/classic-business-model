package com.businessmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.businessmodel.entity.Customer;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {}
