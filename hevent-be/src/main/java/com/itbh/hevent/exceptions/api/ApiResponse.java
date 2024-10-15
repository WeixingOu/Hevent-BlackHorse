package com.itbh.hevent.exceptions.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String status;
    private int code;
    private String message;
    private String path;
    private T data;
    private Map<String, String> errors;

    public ApiResponse(T data) {
        this.message = "Process completed successfully";
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data);
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(HttpStatus status, String message, String path, Map<String, String> errors) {
        this.status = status.name();
        this.code = status.value();
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(message);
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message, String path, Map<String, String> errors) {
        return new ApiResponse<>(status, message, path, errors);
    }

    public ApiResponse(HttpStatus status, String message, String path) {
        this.status = status.name();
        this.code = status.value();
        this.message = message;
        this.path = path;
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message, String path) {
        return new ApiResponse<>(status, message, path);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
