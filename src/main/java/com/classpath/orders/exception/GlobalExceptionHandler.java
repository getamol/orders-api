package com.classpath.orders.exception;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleInvalidOrderId(IllegalArgumentException exception) {
		log.error("exception while fetching invalid order id {}", exception.getMessage());;
		return new Error(100, exception.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Set<String> invalidInputData(MethodArgumentNotValidException exception){
		log.error("exception while fetching invalid order id {}", exception.getMessage());;
		List<ObjectError> allErrors = exception.getAllErrors();
		return allErrors.stream()
				 .map(ObjectError::getDefaultMessage)
				 .collect(Collectors.toSet());
	}
}

@AllArgsConstructor
@Getter
class Error {
	private int code;
	private String message;
}
