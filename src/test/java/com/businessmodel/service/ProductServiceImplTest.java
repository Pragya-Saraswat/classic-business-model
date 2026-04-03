package com.businessmodel.service;

import com.businessmodel.dto.ProductDto;
import com.businessmodel.entity.Product;
import com.businessmodel.entity.ProductLine;
import com.businessmodel.exception.BadRequestException;
import com.businessmodel.repository.ProductRepo;
import com.businessmodel.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class ProductServiceImplTest {
    @Mock
    private ProductRepo productRepo;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testGetProductsSuccess() {
        when(productRepo.findProductByProductLine(any(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(new Product())));
        final Page<ProductDto> result =
                productService.findProductsByProductLine(new ProductLine(),0,10);
        assertFalse(result.isEmpty());
    }
    @Test
    void testGetProductsInvalid() {
        assertThrows(BadRequestException.class,
                () -> productService.findProductsByProductLine(null,0,10));
    }


}
