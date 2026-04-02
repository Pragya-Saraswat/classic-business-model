package com.businessmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.businessmodel.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}