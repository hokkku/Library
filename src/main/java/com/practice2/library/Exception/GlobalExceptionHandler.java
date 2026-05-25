package com.practice2.library.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse>
    handleBookDuplicate(BookAlreadyExistsException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), 409);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
}
