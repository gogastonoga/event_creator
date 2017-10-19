package com.capgemini.wolimierz.controller;

import com.capgemini.wolimierz.controller.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.DataBindingException;

@RestController
public class BaseController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataBindingException.class)
    public ExceptionDto handle(DataBindingException ex) {
        return new ExceptionDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IllegalStateException.class)
    public ExceptionDto handle(IllegalStateException ex) {
        return new ExceptionDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ExceptionDto handle(IllegalArgumentException ex) {
        return new ExceptionDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionDto handle(Exception ex) {
        return new ExceptionDto(ex.getMessage());
    }
}
