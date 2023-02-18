package com.xu.textread.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @author aniki
 * @TableName user
 */
@Data
public class UserVo implements Serializable {
    /**
     * 用户ID
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
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 我的关注
     */
    private long friendsNumber;

    /**
     * 我的收藏
     */
    private long textLikeNumber;

    /**
     * 状态 0-正常 1-封号
     */
    private Integer userStatus;
    
    /**
     * 0-普通, 1- 作者 2-管理员
     */
    private Integer userRole;

}