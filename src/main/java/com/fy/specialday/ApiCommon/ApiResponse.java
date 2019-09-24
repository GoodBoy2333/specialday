package com.fy.specialday.ApiCommon;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fy.specialday.Common.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 通用API接口返回
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {
    /**
     * 通用返回状态
     */
    private Integer status;
    /**
     * 通用返回信息
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
    /**
     * 通用返回数据
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;

    public static ApiResponse success(String message) {
        return new ApiResponse(1, message, "");
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse(0, message, "");
    }

    public static <T> ApiResponse success(String message, T data) {
        return new ApiResponse(1, message, data);
    }

    public static <T> ApiResponse fail(String message, T data) {
        return new ApiResponse(0, message, data);
    }

    public static <T> ApiResponse success(T data) {
        return new ApiResponse(1,"", data);
    }

    public static <T> ApiResponse fail(T data) {
        return new ApiResponse(0, "", data);
    }

    public static <T> ApiResponse status(Status status) {
        return new ApiResponse(status.getStatus(), status.getMessage(), "");
    }

    public static <T> ApiResponse status(Status status,T data) {
        return new ApiResponse(status.getStatus(), status.getMessage(), data);
    }
}