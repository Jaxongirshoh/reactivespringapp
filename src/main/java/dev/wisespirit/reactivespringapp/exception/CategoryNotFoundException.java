package dev.wisespirit.reactivespringapp.exception;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(Throwable cause){
        super(cause);
    }

    public CategoryNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
