package com.kkraljic.shortener.exception;

import com.kkraljic.shortener.dto.AccountResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(final Exception ex) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(final DataIntegrityViolationException ex) {

        final String message = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());

        if (message.contains("PUBLIC.CONSTRAINT_INDEX_7 ON PUBLIC.REGISTERED_URL")) {
            apiError = new ApiError(HttpStatus.BAD_REQUEST, "This URL is already registered. Please choose another.");
            return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());

        } else if (message.contains("PUBLIC.CONSTRAINT_INDEX_E ON PUBLIC.ACCOUNT")) {
            final AccountResponse accountResponse = new AccountResponse();
            accountResponse.setSuccess(false);
            accountResponse.setDescription("This account ID already exists. Please choose another.");

            return new ResponseEntity<>(accountResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UrlNotRegisteredException.class)
    public ResponseEntity<ApiError> handleUrlNotRegistered(final UrlNotRegisteredException ex) {
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // overridden to custom
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        final String message = "Please enter the argument values according to the validation.";
        final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
