package com.zhy.model.dto;

import java.io.Serializable;

/**
 * @author zhy
 */
public class ApiResponse<T> implements Serializable {
    private boolean success;
    private Integer code;
    private String message;
    private T data;

    public ApiResponse(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(String message) {
        return new ApiResponse<>(true, ResponseCode.OK, message, null);
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(true, ResponseCode.OK, message, data);
    }

    public static <T> ApiResponse<T> bad(String message) {
        return new ApiResponse<>(false, ResponseCode.BAD, message, null);
    }

    public static <T> ApiResponse<T> unauthorized(String message) {
        return new ApiResponse<>(false, ResponseCode.UNAUTHORIZED, message, null);
    }

    public static <T> ApiResponse<T> forbidden(String message) {
        return new ApiResponse<>(false, ResponseCode.FORBIDDEN, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

interface ResponseCode {
    int OK = 200;
    int BAD = 500;
    int UNAUTHORIZED = 401;
    int FORBIDDEN = 403;
}
