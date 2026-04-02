package com.businessmodel.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderWithDetailsDto {

    private Integer orderNumber;
    private LocalDate orderDate;
    private String status;
    private String customerName;

    private List<OrderDetailDto> orderDetails;
}