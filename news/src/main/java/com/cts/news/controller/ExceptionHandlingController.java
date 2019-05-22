package com.cts.news.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import com.cts.news.controller.ExceptionHandlingController;
import com.cts.news.entity.ErrorResponse;

@RestController
public class ExceptionHandlingController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleError(Exception ex) {
		LOGGER.info("handleError() execution started!");
		LOGGER.info(ex.getMessage(), ex);
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
		LOGGER.debug("error{}", errorResponse);
		ResponseEntity<ErrorResponse> response = null;

		if (ex instanceof ConstraintViolationException) {
			errorResponse.setReasonCode(HttpStatus.BAD_REQUEST.value());
			ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex;
			Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
			String errorMessage = "invalid input format:";
			for (ConstraintViolation<?> constraintViolation : violations)

			{
				errorMessage += constraintViolation.getMessageTemplate();

			}
			errorMessage = errorMessage.substring(0, errorMessage.length() - 2);
			errorResponse.setErrorMessage(errorMessage);
			response = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);

		}

		else if (ex instanceof MethodArgumentNotValidException) {

			MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
			String message = "";
			List<FieldError> errors = exception.getBindingResult().getFieldErrors();
			for (FieldError errorr : errors) {
				message += errorr.getDefaultMessage() + ", ";
			}
			errorResponse.setErrorMessage(message.substring(0, message.length() - 2));
			return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);

		}

		else if (ex instanceof AuthenticationException) {
			AuthenticationException au = (AuthenticationException) ex;
			errorResponse.setReasonCode(HttpStatus.BAD_REQUEST.value());
			errorResponse.setErrorMessage(au.getLocalizedMessage());
			response = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
		} else if (ex instanceof AccessDeniedException) {
			AuthenticationException au = (AuthenticationException) ex;
			errorResponse.setReasonCode(HttpStatus.BAD_REQUEST.value());
			errorResponse.setErrorMessage(au.getLocalizedMessage());
			response = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.FORBIDDEN);
		}

		else {

			errorResponse.setReasonCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			errorResponse.setErrorMessage(ex.getMessage());

			response = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		LOGGER.info("handleError() execution is completed!");
		return response;
	}

}
