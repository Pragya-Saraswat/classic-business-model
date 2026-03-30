package com.businessmodel.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Integer orderNumber;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;
    private String status;
    private String customerName;
}