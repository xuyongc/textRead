package com.xu.textread.model.request;

import lombok.Data;

/**
 * @author aniki
 * @CreteDate 2023/2/10 14:30
 **/
@Data
public class FriendsRequest {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 关注id
     */
    private Long friendId;

    /**
     * 操作
     */
    private int updateCode;
}
