package com.xu.textread.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.textread.model.domain.Friends;
import com.xu.textread.model.request.FriendsRequest;
import com.xu.textread.model.vo.FriendVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Author xyc
* @description 针对表【friends(关注表)】的数据库操作Service
* @createDate 2023-02-10 13:46:33
*/
public interface FriendsService extends IService<Friends> {

    /**
     * 添加或者删除
     * @param friendsRequest
     * @param request
     * @return
     */
    boolean friendsUpdate(FriendsRequest friendsRequest, HttpServletRequest request);

    /**
     * 查找用户
     * @param userId
     * @return
     */
    List<FriendVo> getFriendsList(long userId);

    /**
     * 获得我的粉丝
     * @param userId
     * @return
     */
    List<FriendVo> getMyFansList(long userId);
}
