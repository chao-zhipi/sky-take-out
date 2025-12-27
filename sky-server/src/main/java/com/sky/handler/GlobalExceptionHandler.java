package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 * @author sky
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex 异常对象
     * @return 异常结果对象
     */
    @ExceptionHandler(BaseException.class)
    public Result<Object> exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 捕获name字段唯一约束异常
     * @param ex 异常对象
     * @return 异常结果对象
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<Object> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String msg = MessageConstant.UNKNOWN_ERROR;
        // 获取异常信息
        String exMessage = ex.getMessage();
        if (exMessage.contains("Duplicate")) {
            // 违反name唯一约束
            String name = exMessage.split(" ")[2];
            name = name.substring(1, name.length() - 1);
            msg = name + MessageConstant.ALREADY_EXISTS;
        }
        log.error("异常信息：{}", msg);
        return Result.error(msg);
    }

}
