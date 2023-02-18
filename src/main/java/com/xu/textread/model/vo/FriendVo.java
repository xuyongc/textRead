package com.xu.textread.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author aniki
 * @CreteDate 2023/2/10 14:49
 **/
@Data
public class FriendVo implements Serializable {

    /**
     * 表ID
     */
    private Long friendsId;
    
    /**
     * 关注id
     */
    private Long friendId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatarUrl;

}
