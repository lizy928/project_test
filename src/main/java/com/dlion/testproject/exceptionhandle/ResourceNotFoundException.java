package com.dlion.testproject.exceptionhandle;

/**
 * @author lzy
 * @date 2020/10/15
 */
public class ResourceNotFoundException extends RuntimeException{

    private String message;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
