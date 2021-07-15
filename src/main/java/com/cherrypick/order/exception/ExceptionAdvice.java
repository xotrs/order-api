package com.cherrypick.order.exception;

import com.cherrypick.order.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected Response badRequestException(BadRequestException e) {
        return Response.error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected Response notFoundException(NotFoundException e) {
        return Response.error(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = String.format("[%s] %s", e.getBindingResult().getFieldError().getField(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return Response.error(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
