package com.practice2.library.Exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String message){
        super(message);
    }
}
