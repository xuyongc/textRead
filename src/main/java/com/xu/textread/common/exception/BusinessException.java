package com.xu.textread.common.exception;

import com.xu.textread.common.ErrorCode;

/**
 * @author aniki
 * @CreteDate 2023/1/26 22:50
 **/
public class BusinessException extends RuntimeException{

    private final int errorCode;

    private final String description;

    public BusinessException(String message, int errorCode, String description) {
        super(message);
        this.errorCode = errorCode;
        this.description = description;
    }

    public BusinessException(String message, int errorCode) {
        this(message,errorCode,null);
    }

    public BusinessException(ErrorCode errorCode) {
        this(errorCode.getMessage(),errorCode.getErrorCode(),errorCode.getDescription());
    }

    public BusinessException(ErrorCode errorCode, String description) {
        this(errorCode.getMessage(),errorCode.getErrorCode(),description);
    }



    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
