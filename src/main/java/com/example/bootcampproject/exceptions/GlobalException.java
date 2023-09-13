package com.example.bootcampproject.exceptions;

// @ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GlobalException extends Exception{

    private static final long serialVersionUID = 1L;

    public GlobalException(String message){
        super(message);
    }
}