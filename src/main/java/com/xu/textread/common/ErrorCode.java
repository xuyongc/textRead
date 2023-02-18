package com.xu.textread.common;

/**
 * @author aniki
 * @CreteDate 2023/1/26 22:40
 **/
public enum ErrorCode {
    /**
     * 异常返回
     */
    SYSTEM_ERROR("系统异常", 50000),

    PARAMS_ERROR("请求参数异常", 40001),

    PARAMS_NULL_ERROR("请求参数为空", 40002),

    REQUEST_ERROR("请求错误", 40003),

    NULL_ERROR("请求数据为空", 40004),

    NOT_LOGIN_ERROR("未登录", 30200),

    NO_AUTH_ERROR("没权限", 70002),

    USER_ALREADY_REGISTERED("用户已经注册", 40017),

    USER_NOT_REGISTER("用户未注册", 40103),

    USER_DATA_ERROR("用户数据不存在", 40105),
    ;

    private final String message;

    private final int errorCode;

    private final String description;

    ErrorCode(String message, int errorCode, String description) {
        this.message = message;
        this.errorCode = errorCode;
        this.description = description;
    }

    ErrorCode(String message, int errorCode) {
        this(message, errorCode, "");
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
