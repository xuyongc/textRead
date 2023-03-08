package com.xu.textread.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xu.textread.common.BaseResponse;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.Results;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.model.domain.Friends;
import com.xu.textread.model.request.FriendsRequest;
import com.xu.textread.model.vo.FriendVo;
import com.xu.textread.service.FriendsService;
import com.xu.textread.service.UserService;
import com.xu.textread.utils.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xyc
 * @CreteDate 2023/2/17 15:03
 * 关注表
 **/
@Controller
@ResponseBody
@RequestMapping("/friends")
public class FriendsController {

    @Resource
    private UserService userService;

    @Resource
    private FriendsService friendsService;

    /**
     * 点击关注修改
     *
     * @param friendsRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addFriends(@RequestBody FriendsRequest friendsRequest, HttpServletRequest request) {
        Long userId = friendsRequest.getUserId();
        Long friendId = friendsRequest.getFriendId();


        if (!NumberUtils.isNullAndLessZero(userId, friendId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        boolean result = friendsService.friendsAdd(friendsRequest, request);

        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return Results.success(friendsRequest.getFriendId());
    }

//    /**
//     * 取消关注
//     *
//     * @param friendsId
//     * @param userId
//     * @param request
//     * @return
//     */
//    @GetMapping("/delete")
//    public BaseResponse<Boolean> deleteFriends(long friendsId, long userId, HttpServletRequest request) {
//
//        //改为post
//        if (!NumberUtils.isNullAndLessZero(friendsId, userId)) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//
//        if (userId != userService.getLoginUserId(request)) {
//            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是自己");
//        }
//
//        QueryWrapper<Friends> friendsQueryWrapper = new QueryWrapper<>();
//        friendsQueryWrapper.eq("friendsId", friendsId);
//        long count = friendsService.count(friendsQueryWrapper);
//
//        if (count == 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "你已经删除");
//        }
//
//        boolean result = friendsService.remove(friendsQueryWrapper);
//
//        if (!result) {
//            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
//        }
//
//        return Results.success(true);
//    }

    /**
     * 取消关注
     *
     * @param friendId
     * @param userId
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public BaseResponse<Boolean> deleteFriends(long friendId, long userId, HttpServletRequest request) {

        //改为post
        if (!NumberUtils.isNullAndLessZero(friendId, userId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (userId != userService.getLoginUserId(request)) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是自己");
        }

        QueryWrapper<Friends> friendsQueryWrapper = new QueryWrapper<>();
        friendsQueryWrapper.eq("friendId", friendId).eq("userId",userId);
        long count = friendsService.count(friendsQueryWrapper);

        if (count == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "你已经删除");
        }

        boolean result = friendsService.remove(friendsQueryWrapper);

        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        return Results.success(true);
    }

    /**
     * 查询关注列表
     *
     * @param userId
     * @param request
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<FriendVo>> listFriend(long userId, HttpServletRequest request) {

        if (userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (userId != userService.getLoginUserId(request)) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是自己");
        }

        // todo 不要查出自己防止脏脏数据

        List<FriendVo> friendVoList = friendsService.getFriendsList(userId);

        if (friendVoList.isEmpty()) {
            return Results.success(new ArrayList<>());
        }

        return Results.success(friendVoList);
    }


    /**
     * 查询我的粉丝
     *
     * @param userId 这个是自己的userId 在SQL查询里面作为friendId查询
     * @param request
     * @return
     */
    @GetMapping("/fans/list")
    public BaseResponse<List<FriendVo>> listFan(long userId, HttpServletRequest request) {

        if (userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (userId != userService.getLoginUserId(request)) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是自己");
        }

        // todo 不要查出自己防止脏脏数据

        List<FriendVo> friendVoList = friendsService.getMyFansList(userId);

        if (friendVoList.isEmpty()) {
            return Results.success(new ArrayList<>());
        }

        return Results.success(friendVoList);
    }
}
