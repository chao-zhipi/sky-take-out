package com.sky.exception;

/**
 * 业务异常
 * @author sky
 */
public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }

}
