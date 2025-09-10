package com.project.TaskApp.exceptions;

import com.project.TaskApp.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//from spring framework //error handele globaly
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//catch specific error
    public ResponseEntity<Response<?>> handleAllUnknownException(Exception ex){
        Response<?> response =Response.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
        return  new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<?>> handleNotFoundException(NotFoundException ex){
        Response<?> response =Response.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response<?>> handleBadRequestException(BadRequestException ex){
        Response<?> response =Response.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
