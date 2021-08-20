package com.zhy.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 统一接口返回
 *
 * @author zhy
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
    private boolean success;
    private Integer code;
    private String message;
    private T data;

    public ApiResponse(boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(true, ResponseCode.OK, null, null);
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

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, ResponseCode.ERROR, message, null);
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
    int OK = 200; // 请求处理成功
    int BAD = 400; // 请求处理失败
    int UNAUTHORIZED = 401; // 请求未认证
    int FORBIDDEN = 403; // 请求未授权
    int ERROR = 500; // 严重或未知错误
}
