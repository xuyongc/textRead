package com.xu.textread.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


/**
 * 文章表
 * @TableName text
 */
@TableName(value ="text")
@Data
public class Text implements Serializable {
    /**
     * 文章Id
     */
    @TableId(type = IdType.AUTO)
    private Long textId;

    /**
     * 文章标题
     */
    private String textTitle;

    /**
     * 作者Id
     */
    private Long textAuthorId;

    /**
     * 封面地址
     */
    private String showImgUrl;

    /**
     * 文章内容
     */
    private String userText;

    /**
     * 文章内容html
     */
    private String textHtml;

    /**
     * 文章介绍
     */
    private String textIntroduce;

    /**
     * 喜欢数
     */
    private Long textLikeNumber;

    /**
     * 收藏数量
     */
    private Long textFavoriteNumber;

    /**
     * 状态 0-正常 1-审核中 2-下架
     */
    private Integer textStatus;

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