package com.sky.exception;

/**
 * 登录失败
 * @author sky
 */
public class LoginFailedException extends BaseException{
    public LoginFailedException(String msg){
        super(msg);
    }
}
