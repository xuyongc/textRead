package com.xu.textread.model.request;

import lombok.Data;

/**
 * @Author xyc
 * @CreteDate 2023/2/11 14:26
 **/
@Data
public class TextLikeRequest {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 关注id
     */
    private Long textId;

}
