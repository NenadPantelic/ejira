package net.radionica.ejira.controller.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.radionica.ejira.api.ApiError;
import net.radionica.ejira.exception.InvalidCredentialsException;
import net.radionica.ejira.exception.NonExistentResourceException;
import net.radionica.ejira.exception.UnauthorizedException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiError> handle(InvalidCredentialsException ex){
        ApiError error = new ApiError(ex.getMessage());
        return new ResponseEntity<ApiError>(error, HttpStatus.NOT_ACCEPTABLE);
    }
	
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> handle(UnauthorizedException ex){
        ApiError error = new ApiError(ex.getMessage());
        return new ResponseEntity<ApiError>(error, HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(NonExistentResourceException.class)
    public ResponseEntity<ApiError> handle(NonExistentResourceException ex){
        ApiError error = new ApiError(ex.getMessage());
        return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND);
    }   

}
