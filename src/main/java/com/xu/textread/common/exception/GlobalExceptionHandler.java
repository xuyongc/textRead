package com.xu.textread.common.exception;

import com.xu.textread.common.BaseResponse;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.Results;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author xyc
 * @CreteDate 2023/1/26 23:00
 **/

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public <T> BaseResponse<T> businessException(BusinessException e){
        return Results.error(e.getMessage(),e.getErrorCode(),e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public <T>BaseResponse <T> businessException(RuntimeException e){
        return Results.error(ErrorCode.SYSTEM_ERROR);
    }
}
