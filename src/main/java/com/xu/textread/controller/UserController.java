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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xyc
 * @CreteDate 2023/1/26 22:19
 **/
@Controller
@ResponseBody
@RequestMapping("/user")
@CrossOrigin(origins = {"http://127.0.0.1:5173/"})
public class UserController {

//    自主new出来的对象不被spring作为javaBean对象管理起来，从而导致该对象内的所有@Autowied也都不会被自动注入。
//    在自己里面用别人的,别人用自己的注入会bean依赖循环
    @Resource
    private UserService userService;

    @Resource
    private TextService textService;

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
    public BaseResponse<Long> update(@RequestBody UserUpdateRequest user, HttpServletRequest request) {
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
     * 获取当前用户，从数据库获取
     *
     * @param request
     * @return
     */
    @GetMapping("/search/sql/current/")
    public BaseResponse<UserVo> getCurrentBySql(HttpServletRequest request) {
        UserVo loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        User currentUser = userService.getById(loginUser.getUserId());

        return Results.success(userService.getSafeUser(currentUser));
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

        return Results.success(loginUser);
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
     * 修改上传同用
     *
     * @param
     * @param file
     *
     * @param request
     * @return
     */
    @PostMapping("/avatarUrl")
    public BaseResponse<String> avatarUrlUpload(Long userId,@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        if (file == null || !NumberUtils.isNumberLessZero(userId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String upload = userService.userAvatarUrlUpload(userId, request, file);
        if (upload == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        return Results.success(upload);
    }
}
