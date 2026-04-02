package com.businessmodel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.businessmodel.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Page<Customer> findByCountry(String country, Pageable pageable);
    Page<Customer> findAllByOrderByCreditLimitDesc(Pageable pageable);
}