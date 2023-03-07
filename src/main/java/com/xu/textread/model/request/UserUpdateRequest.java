package com.xu.textread.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户表
 * @Author xyc
 * @TableName user
 */
@Data
public class UserUpdateRequest implements Serializable {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 账户
     */
    private String userAccount;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}