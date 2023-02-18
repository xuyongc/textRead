package com.xu.textread.common;

import java.io.Serializable;

/**
 * @author aniki
 * @CreteDate 2023/1/26 22:32
 **/
public class BaseResponse<T> implements Serializable {

    private final String massage;

    private final T Data;

    private final int code;

    private final String description;

    public BaseResponse(String massage, T data, int errorCode, String description) {
        this.massage = massage;
        Data = data;
        this.code = errorCode;
        this.description = description;
    }

    public BaseResponse(String massage,  int errorCode,String description) {
        this(massage,null,errorCode,description);
    }

    public BaseResponse(String massage, T data, int errorCode) {
        this(massage,data,errorCode,null);
    }

    public BaseResponse(String massage, int errorCode) {
        this(massage,null,errorCode);
    }

    public String getMassage() {
        return massage;
    }

    public T getData() {
        return Data;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
