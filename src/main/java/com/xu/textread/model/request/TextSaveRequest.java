package com.xu.textread.model.request;

import lombok.Data;

/**
 * @author aniki
 * @CreteDate 2023/2/8 19:11
 **/
@Data
public class TextSaveRequest {

    /**
     * 文章标题
     */
    private String textTitle;

    /**
     * 作品文件
     */
    private String userText;

    /**
     * 作品文件
     */
    private String textHtml;

    /**
     * 作者Id
     */
    private Long textAuthorId;

    /**
     * 职责
     */
    private Long userRole;


    /**
     * 封面地址
     */
    private String showImgUrl;

    /**
     * 文章介绍
     */
    private String textIntroduce;
}
