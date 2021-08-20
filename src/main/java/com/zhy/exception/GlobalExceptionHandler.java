package com.zhy.exception;


import com.zhy.model.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhy
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(value = ApiException.class)
    public ApiResponse<?> handle(ApiException e) {
        LOGGER.warn(e.getMessage());
        return ApiResponse.bad(e.getMessage());
    }

    /**
     * 参数校验失败异常处理
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse<?> handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + " " + fieldError.getDefaultMessage();
                LOGGER.warn(message);
            }
        }
        return ApiResponse.bad(message);
    }

    /**
     * 参数校验失败异常处理
     */
    @ExceptionHandler(value = BindException.class)
    public ApiResponse<?> handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + " " + fieldError.getDefaultMessage();
                LOGGER.warn(message);
            }
        }
        return ApiResponse.bad(message);
    }

    /**
     * Exception异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public ApiResponse<?> handle(Exception e) {
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        return ApiResponse.bad(e.getMessage());
    }
}
