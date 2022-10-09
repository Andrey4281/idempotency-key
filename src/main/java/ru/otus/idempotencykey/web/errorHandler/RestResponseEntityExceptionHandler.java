package ru.otus.idempotencykey.web.errorHandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final static String IDEMPOTENCY_KEY_VIOLATION_ERROR_REG_EXP
            = ".*idempotency_key_pkey.*ConstraintViolationException.*";

    @ExceptionHandler(value
            = {DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleUserNotFoundError(
            RuntimeException ex, WebRequest request) {

        if (ex.getMessage().matches(IDEMPOTENCY_KEY_VIOLATION_ERROR_REG_EXP)) {
            String responseBody = "Duplicate by idempotency key";
            return handleExceptionInternal(ex, responseBody,
                    new HttpHeaders(), HttpStatus.CONFLICT, request);
        } else {
            String responseBody = "Internal server error";
            return handleExceptionInternal(ex, responseBody,
                    new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }
}
