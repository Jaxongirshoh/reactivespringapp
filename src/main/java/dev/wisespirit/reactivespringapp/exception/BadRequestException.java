package dev.wisespirit.reactivespringapp.exception;

public class BadRequestException extends Exception{

    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(String message,Throwable cause){
        super(message,cause);
    }

    public BadRequestException(Throwable cause){
        super(cause);
    }

}
