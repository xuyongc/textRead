package com.xu.textread.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName comments
 */
@TableName(value ="comments")
@Data
public class Comments implements Serializable {
    /**
     * 表ID
     */
    @TableId(type = IdType.AUTO)
    private Long commentsId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 文章id
     */
    private Long textId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 逻辑-1删除 -0存在
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}