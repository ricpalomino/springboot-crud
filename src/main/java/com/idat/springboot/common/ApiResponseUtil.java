package com.idat.springboot.common;

public class ApiResponseUtil {

    public ApiResponseUtil() {
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(ResponseCode.SUCCESS.name(), message, data);
    }

    public static <T> ApiResponse<T> error(ResponseCode responseCode, String message, T data) {
        return new ApiResponse<>(responseCode.name(), message, data);
    }

}
