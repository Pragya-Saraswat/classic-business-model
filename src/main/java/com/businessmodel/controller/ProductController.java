package com.businessmodel.controller;

import com.businessmodel.dto.ProductDto;
import com.businessmodel.entity.ProductLine;
import com.businessmodel.repository.ProductLineRepo;
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
    private final ProductLineRepo productLineRepo;

    @GetMapping
    public ResponseEntity<?> getProductsByProductLine(
            @RequestParam String productLine) {

        try {

            ProductLine pl = productLineRepo.findById(productLine)
                    .orElseThrow(() -> new RuntimeException("ProductLine not found"));

            List<ProductDto> products =
                    productService.findProductsByProductLine(pl);

            return ResponseEntity.ok(products);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}