package com.businessmodel.controller;

import com.businessmodel.dto.ProductDto;
import com.businessmodel.entity.ProductLine;
import com.businessmodel.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findProductsByProductLine(@RequestParam ProductLine productLine) {
        return new  ResponseEntity<>(productService.findProductsByProductLine(productLine), HttpStatus.OK);
    }

}