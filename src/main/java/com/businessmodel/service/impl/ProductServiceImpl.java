package com.businessmodel.service.impl;

import com.businessmodel.dto.ProductDto;
import com.businessmodel.entity.Product;
import com.businessmodel.exception.BadRequestException;
import com.businessmodel.exception.ResourceNotFoundException;
import com.businessmodel.mapper.ProductMapper;
import com.businessmodel.repository.ProductRepo;
import com.businessmodel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepo productRepo;

	@Override
	public Page<ProductDto> findProductsByProductLine(String productLine, int page, int size) {

	    if (productLine == null || productLine.isBlank()) {
	        throw new BadRequestException("Product line cannot be empty");
	    }

	    if (page < 0 || size <= 0) {
	        throw new BadRequestException("Invalid pagination parameters");
	    }

	    Pageable pageable = PageRequest.of(page, size);

	    Page<Product> products = productRepo.findByProductLineProductLine(productLine, pageable);

	    if (products.isEmpty()) {
	        throw new ResourceNotFoundException("No products found for product line: " + productLine);
	    }

	    return products.map(ProductMapper::toProductDto);
	}
}