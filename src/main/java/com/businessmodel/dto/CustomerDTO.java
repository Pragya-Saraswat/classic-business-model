package com.businessmodel.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @NotNull
    private Integer customerNumber;
    @NotNull
    @NotBlank
    private String customerName;
    @NotNull
    private String country;
    @NotNull
    private String phone;
    private BigDecimal creditLimit;

}
