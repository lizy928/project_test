package com.dlion.testproject.exceptionhandle;

import lombok.Data;

/**
 * @author lzy
 * @date 2020/10/15
 */
@Data
public class ErrorResponse {

    private String message;
    private String errorTypeName;

    public ErrorResponse(Exception e) {
        this(e.getClass().getName(), e.getMessage());
    }

    public ErrorResponse(String errorTypeName, String message) {
        this.errorTypeName = errorTypeName;
        this.message = message;
    }
}
