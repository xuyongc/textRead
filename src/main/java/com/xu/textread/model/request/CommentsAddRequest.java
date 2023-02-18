package com.xu.textread.model.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表
 * @author aniki
 * @TableName comments
 */
@Data
public class CommentsAddRequest implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 评论id
     */
    private Long textId;
    /**
     * 评论内容
     */
    private String content;


}