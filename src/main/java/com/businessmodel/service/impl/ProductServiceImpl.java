package com.businessmodel.service.impl;

import com.businessmodel.dto.ProductDto;
import com.businessmodel.entity.Product;
import com.businessmodel.entity.ProductLine;
import com.businessmodel.mapper.ProductMapper;
import com.businessmodel.repository.ProductRepo;
import com.businessmodel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<ProductDto> findProductsByProductLine(ProductLine productLine) {
        List<Product> products=productRepo.findProductByProductLine(productLine);
        List<ProductDto> productDto=new ArrayList<>();
        products.forEach(p->productDto.add(ProductMapper.toProductDto(p)));
        return productDto;
    }


}