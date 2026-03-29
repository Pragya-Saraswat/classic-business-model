package com.businessmodel.dto;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderWithDetailsDto {

    private int orderNumber;
    private Date orderDate;
    private String status;
    private String customerName;

    private List<OrderDetailDto> orderDetails;
}