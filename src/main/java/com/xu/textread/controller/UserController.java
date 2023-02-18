package com.xu.textread.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xu.textread.common.BaseResponse;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.Results;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.model.domain.Friends;
import com.xu.textread.model.domain.TextLike;
import com.xu.textread.model.domain.User;
import com.xu.textread.model.dto.TextSearchQuery;
import com.xu.textread.model.request.*;
import com.xu.textread.model.vo.FriendVo;
import com.xu.textread.model.vo.TextVo;
import com.xu.textread.model.vo.UserVo;
import com.xu.textread.service.*;
import com.xu.textread.utils.BeanUtil;
import com.xu.textread.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aniki
 * @CreteDate 2023/1/26 22:19
 **/
@Controller
@ResponseBody
@RequestMapping("/user")
@CrossOrigin(origins = {"http://127.0.0.1:5173/"})
public class UserController {


//    自主new出来的对象不被spring作为javaBean对象管理起来，从而导致该对象内的所有@Autowied也都不会被自动注入。

    @Resource
    private UserService userService;

    @Resource
    private TextService textService;

    /**
     * 收藏夹
     */
    @Resource
    private FavoritesService favoritesService;

    /**
     * 点赞表
     */
    @Resource
    private TextLikeService textLikeService;

    /**
     * 关注
     */
    @Resource
    private FriendsService friendsService;

    /**
     * 评论表
     */
    @Resource
    private CommentsService commentsService;


    /**
     * 登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<UserVo> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();


        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        UserVo userVo = userService.userLogin(userAccount, userPassword, request);

        return Results.success(userVo);
    }

    /**
     * 注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {

        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String userCheckPassword = userRegisterRequest.getUserCheckPassword();


        if (StringUtils.isAnyBlank(userAccount, userPassword, userCheckPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long userId = userService.userRegister(userAccount, userPassword, userCheckPassword);
        return Results.success(userId);
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public BaseResponse<Long> logout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        return Results.success(userService.logout(request));
    }

    /**
     * 修改用户，用户自己修改
     *
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Long> update(@RequestBody User user, HttpServletRequest request) {
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserVo loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        long userId = userService.updateUser(user, loginUser);

        return Results.success(userId);
    }

    /**
     * 删除用户，用户销号，管理员删除账号（暂定）
     *
     * @param userId
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> delete(long userId, HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserVo loginUser = userService.getLoginUser(request);

        return Results.success(userService.deleteUser(userId, loginUser));
    }


    /**
     * 获取当前用户，从当前登录获取之后有积分要从数据库获取最新信息
     *
     * @param request
     * @return
     */
    @GetMapping("/search/current")
    public BaseResponse<UserVo> getCurrent(HttpServletRequest request) {
        UserVo loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        User currentUser = userService.getById(loginUser.getUserId());

        return Results.success(userService.getSafeUser(currentUser));
    }


    /**
     * 搜索查询分页，默认分页，用户查询
     *
     * @param textSearchQuery
     * @return
     */
    @GetMapping("/search/list/text/page")
    public BaseResponse<List<TextVo>> textListPage(TextSearchQuery textSearchQuery) {

        if (textSearchQuery == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String searchText = textSearchQuery.getSearchText();
        int pageSize = textSearchQuery.getPageSize();
        int pageNumber = textSearchQuery.getPageNumber();

        if (StringUtils.isAnyBlank(searchText) || pageSize <= 0 || pageNumber <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        List<TextVo> textVoList = textService.searchText(searchText, pageSize, pageNumber);

        return Results.success(textVoList);
    }

    /**
     * 点击关注修改
     *
     * @param friendsRequest
     * @param request
     * @return
     */
    @PostMapping("/friends/update")
    public BaseResponse<Long> updateFriends(@RequestBody FriendsRequest friendsRequest, HttpServletRequest request) {
        Long userId = friendsRequest.getUserId();
        Long friendId = friendsRequest.getFriendId();
        int updateCode = friendsRequest.getUpdateCode();


        if (!NumberUtils.isNullAndLessZero(updateCode,userId,friendId)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        boolean result = friendsService.friendsUpdate(friendsRequest, request);

        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        return Results.success(friendsRequest.getFriendId());
    }

    /**
     * 列表删除
     *
     * @param friendsId
     * @param userId
     * @param request
     * @return
     */
    @GetMapping("/friends/delete")
    public BaseResponse<Boolean> deleteFriends(long friendsId, long userId, HttpServletRequest request) {

        if (!NumberUtils.isNullAndLessZero(friendsId, userId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (userId != userService.getLoginUser(request).getUserId()) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是自己");
        }

        QueryWrapper<Friends> friendsQueryWrapper = new QueryWrapper<>();
        friendsQueryWrapper.eq("friendsId", friendsId);
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
    @GetMapping("/friends/list")
    public BaseResponse<List<FriendVo>> listFriend(long userId, HttpServletRequest request) {

        if (userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (userId != userService.getLoginUser(request).getUserId()) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是自己");
        }

        List<FriendVo> friendVoList = friendsService.getFriendsList(userId);

        if (friendVoList.isEmpty()) {
            return Results.success(new ArrayList<>());
        }

        return Results.success(friendVoList);
    }

    @PostMapping("/favorites/update")
    public BaseResponse<Long> updateFavorites(FavoritesUpdateRequest favoritesUpdateRequest, HttpServletRequest request) {

        Long userId = favoritesUpdateRequest.getUserId();
        Long textId = favoritesUpdateRequest.getTextId();

        if (!NumberUtils.isNullAndLessZero(userId, textId)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        long result = favoritesService.favoritesUpdate(favoritesUpdateRequest, request);

        if (result <= 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库插入失败");
        }

        return Results.success(result);
    }

    @GetMapping("/favorites/list")
    public BaseResponse<List<TextVo>> listFavourites(long userId, HttpServletRequest request) {
        if (userId <= 0) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR);
        }

        List<TextVo> textVoList = favoritesService.favoriteVosList(userId, request);

        if (textVoList.isEmpty()) {
            return Results.success(new ArrayList<>());
        }

        return Results.success(textVoList);
    }



    @PostMapping("/comments/add")
    public BaseResponse<Long> addComment(@RequestBody CommentsAddRequest commentsAddRequest, HttpServletRequest request) {

        Long userId = commentsAddRequest.getUserId();
        Long textId = commentsAddRequest.getTextId();
        if (!NumberUtils.isNullAndLessZero(userId, textId)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        String content = commentsAddRequest.getContent();
        if (StringUtils.isAnyBlank(content)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long result = commentsService.AddComment(commentsAddRequest, request);

        if (result <= 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        return Results.success(result);
    }

    @PostMapping("/comments/delete")
    public BaseResponse<Boolean> deleteComments(long userId, long commentId, HttpServletRequest request) {
        if (!NumberUtils.isNumberLessZero(userId, commentId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (userId != userService.getLoginUser(request).getUserId()) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是本人操作");
        }

        boolean result = commentsService.removeById(commentId);

        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        return Results.success(true);
    }

    @PostMapping("/comments/update")
    public BaseResponse<Boolean> updateComments(CommentsUpdateRequest updateRequest, HttpServletRequest request) {
        Long userId = updateRequest.getUserId();
        Long commentId = updateRequest.getCommentsId();

        if (!NumberUtils.isNumberLessZero(userId, commentId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String content = updateRequest.getContent();
        if (StringUtils.isAnyBlank(content)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Boolean result = commentsService.updateComment(updateRequest, request);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库插入失败");
        }

        return Results.success(true);
    }
}
