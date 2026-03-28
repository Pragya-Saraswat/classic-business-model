package com.businessmodel.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailDto {

    private String productCode;
    private String productName;
    private int quantityOrdered;
    private double priceEach;
}