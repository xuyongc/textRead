package com.xu.textread.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章表
 * @Author xyc
 * @TableName text
 */
@Data
public class TextVo implements Serializable {
    /**
     * 文章Id
     */
    private Long textId;

    /**
     * 作者Id
     */
    private Long textAuthorId;

    /**
     * 文章标题
     */
    private String textTitle;

    /**
     * 作者名字
     */
    private String textAuthorName;

    /**
     * 文章介绍
     */
    private String textIntroduce;

    /**
     * 封面地址
     */
    private String showImgUrl;

    /**
     * 喜欢数
     */
    private Long textLikeNumber;

    /**
     * 收藏数量
     */
    private Long textFavoriteNumber;
}