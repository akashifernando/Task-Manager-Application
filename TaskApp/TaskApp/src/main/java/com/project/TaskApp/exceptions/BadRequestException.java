package com.project.TaskApp.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String ex){
        super(ex);
    }
}
