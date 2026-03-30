package com.businessmodel.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AmountDto {
	@NotNull
	private Integer customerNumber;
	@NotBlank
	private String checkNumber;
	@NotNull
	private LocalDate paymentDate;
	@NotNull
	private BigDecimal amount;

	private BigDecimal totalAmount;

}
