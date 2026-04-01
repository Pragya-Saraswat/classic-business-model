package com.businessmodel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private String errorMessage;
    private int status;
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate now;
    private String uri;
}
