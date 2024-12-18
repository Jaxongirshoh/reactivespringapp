package dev.wisespirit.reactivespringapp.util;

public class ApiResponse<T> {
    private T data;
    private String message;
    private int statusCode;

    public ApiResponse(){
    }
    public ApiResponse(T data,int statusCode){
        this.data = data;
        this.statusCode = statusCode;
    }
    public ApiResponse(T data,int statusCode,String message){
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }

    public ApiResponse(T data,String message){
        this.data = data;
        this.message = message;
    }


}
