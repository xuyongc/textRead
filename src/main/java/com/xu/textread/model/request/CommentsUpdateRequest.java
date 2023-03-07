package com.xu.textread.model.request;

import lombok.Data;

/**
 * @Author xyc
 * @CreteDate 2023/2/11 16:02
 **/
@Data
public class CommentsUpdateRequest {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论id
     */
    private Long commentsId;
}
