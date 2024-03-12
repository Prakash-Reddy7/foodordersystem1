package com.foodordersystem.Exception;
import com.foodordersystem.Entity.DiningTable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TableAlreadyBookedException.class)
    public ResponseEntity<DiningTable> handleTableAlreadyBookedException(TableAlreadyBookedException ex) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}