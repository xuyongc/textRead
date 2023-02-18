package com.xu.textread.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.model.domain.Friends;
import com.xu.textread.model.request.FriendsRequest;
import com.xu.textread.model.vo.FriendVo;
import com.xu.textread.service.FriendsService;
import com.xu.textread.mapper.FriendsMapper;
import com.xu.textread.service.UserService;
import com.xu.textread.utils.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author aniki
 * @description 针对表【friends(关注表)】的数据库操作Service实现
 * @createDate 2023-02-10 13:46:33
 */
@Service
public class FriendsServiceImpl extends ServiceImpl<FriendsMapper, Friends>
        implements FriendsService {

    @Resource
    private UserService userService;

    @Resource
    private FriendsMapper friendsMapper;

    @Override
    public boolean friendsUpdate(FriendsRequest friendsRequest, HttpServletRequest request) {
        // todo 参数判断

        Long userId = friendsRequest.getUserId();
        if (!userId.equals(userService.getLoginUser(request).getUserId())) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是自己添加");
        }


        Long friendId = friendsRequest.getFriendId();

        if (userId.equals(friendId)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"自己不能添加自己");
        }

        QueryWrapper<Friends> friendsQueryWrapper = new QueryWrapper<>();
        friendsQueryWrapper.eq("userId", userId).eq("friendsId", friendId);
        long count = this.count(friendsQueryWrapper);

        Friends friends = BeanUtil.copyProperties(friendsRequest, new Friends());

        boolean result = false;
        //添加
        int updateCode = friendsRequest.getUpdateCode();
        if (updateCode == 0) {
            if (count != 0) {
                throw new BusinessException(ErrorCode.REQUEST_ERROR, "已经关注");
            }

            result = this.save(friends);
        }
        // todo 这段代码写的真垃圾之后记得优化，写的真TM垃圾
        if (updateCode == 1) {
            if (count == 0) {
                throw new BusinessException(ErrorCode.REQUEST_ERROR, "已经删除");
            }
            result = this.removeById(friends);
        }

        return result;
    }

    @Override
    public List<FriendVo> getFriendsList(long userId){
        if (userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

       return friendsMapper.selectFriendVoList(userId);
    }
}




