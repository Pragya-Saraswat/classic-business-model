package com.businessmodel.service;

import com.businessmodel.dto.ProductDto;
import com.businessmodel.entity.ProductLine;

import java.util.List;

public interface ProductService {
    public List<ProductDto> findProductsByProductLine(ProductLine productLine);
}
