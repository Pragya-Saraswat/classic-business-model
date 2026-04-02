package com.businessmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.businessmodel.entity.Office;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepo extends JpaRepository<Office, String> {

}