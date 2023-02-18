package com.xu.textread.common;

/**
 * @author aniki
 * @CreteDate 2023/1/26 22:31
 **/
public class Results {

    public static <T> BaseResponse<T> success(){
        return new BaseResponse<>("请求成功",200);
    }

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>("请求成功",data ,200);
    }

    public static <T> BaseResponse<T> error(String massage, int errorCode, String description){
        return new BaseResponse<>(massage,errorCode,description);
    }

    public static <T> BaseResponse<T> error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode.getMessage(),errorCode.getErrorCode(),errorCode.getDescription());
    }
}
