package com.xu.textread.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.textread.model.domain.Friends;
import com.xu.textread.model.vo.FriendVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Author xyc
* @description 针对表【friends(关注表)】的数据库操作Mapper
* @createDate 2023-02-10 13:46:33
* @Entity generator.domain.Friends
*/
public interface FriendsMapper extends BaseMapper<Friends> {

    /**
     * 查询FriendVoList
     * @param userId
     * @return
     */
    List<FriendVo> selectFriendVoList(@Param("userId") long userId);

    /**
     * 查询我的粉丝
     * @param userId
     * @return
     */
    List<FriendVo> selectMyFanList(@Param("userId")long userId);
}




