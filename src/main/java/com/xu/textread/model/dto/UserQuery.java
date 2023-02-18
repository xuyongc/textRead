package com.xu.textread.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xu.textread.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户表
 * @author aniki
 * @TableName user
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends PageRequest {
    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 0-普通, 1- 作者 2-管理员
     */
    private Integer userRole;

}