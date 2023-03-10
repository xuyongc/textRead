package com.xu.textread.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author aniki
 */
@Data
public class TextViewVo implements Serializable {
    /**
     * 文章Id
     */
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
     * 文章内容html
     */
    private String textHtml;

    /**
     * 喜欢数
     */
    private Long textLikeNumber;

    /**
     * 收藏数量
     */
    private Long textFavoriteNumber;
}