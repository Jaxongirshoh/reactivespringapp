package dev.wisespirit.reactivespringapp.util;

public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean success;
    public ApiResponse(String message){
        this.message = message;
        this.success = false;
    }
    public ApiResponse(T data,String message){
        this.data = data;
        this.message = message;
        this.success = true;
    }

}
