package com.businessmodel.service;

import com.businessmodel.dto.ProductDto;

import org.springframework.data.domain.Page;

public interface ProductService {
	Page<ProductDto> findProductsByProductLine(String productLine, int page, int size);
}
