package com.businessmodel.exception;

import com.businessmodel.dto.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ResponseBody
    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleException(ResourceNotFoundException e, HttpServletRequest request) {
        return new ErrorDto(e.getMessage(), HttpStatus.NOT_FOUND.value(),LocalDate.now(),request.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(BusinessException e, HttpServletRequest request) {
      return new ErrorDto(e.getMessage(), HttpStatus.BAD_REQUEST.value(),LocalDate.now(),request.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(BadRequestException e, HttpServletRequest request) {
        return new ErrorDto(e.getMessage(), HttpStatus.BAD_REQUEST.value(),LocalDate.now(),request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleGeneral(Exception e, HttpServletRequest request) {
        return new ErrorDto("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDate.now(),request.getRequestURI());
    }

}
