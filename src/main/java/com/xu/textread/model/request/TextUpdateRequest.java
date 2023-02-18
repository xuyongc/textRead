package com.xu.textread.model.request;

import lombok.Data;

/**
 * @author aniki
 * @CreteDate 2023/2/9 19:11
 **/
@Data
public class TextUpdateRequest {
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
     * 文章介绍
     */
    private String textIntroduce;
}
