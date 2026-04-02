package com.businessmodel.controller;

import com.businessmodel.dto.ProductDto;
import com.businessmodel.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ------------ By ProductLine ---------
    @GetMapping
    public Page<ProductDto> findProductsByProductLine(
            @RequestParam String productLine,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return productService.findProductsByProductLine(productLine, page, size);
    }
}