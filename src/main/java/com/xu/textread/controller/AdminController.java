package com.xu.textread.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xu.textread.common.BaseResponse;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.Results;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.model.domain.Text;
import com.xu.textread.model.domain.User;
import com.xu.textread.model.dto.UserQuery;
import com.xu.textread.model.vo.TextVo;
import com.xu.textread.model.vo.UserVo;
import com.xu.textread.service.TextService;
import com.xu.textread.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

import static com.xu.textread.constant.AuthorTextConstant.ERROR_TEXT;
import static com.xu.textread.constant.AuthorTextConstant.UNDER_REVIEW_TEXT;
import static com.xu.textread.constant.UserRoleConstant.AUTHOR_ROLE;
import static com.xu.textread.constant.UserStatusConstant.DEFAULT_USER;
import static com.xu.textread.constant.UserStatusConstant.SUSPENDED_USER;

/**
 * @Author xyc
 * @CreteDate 2023/2/9 11:20
 * 管理员表
 **/
@ResponseBody
@RequestMapping("/admin")
@Controller
public class AdminController {

    @Resource
    private UserService userService;

    @Resource
    private TextService textService;

    /**
     * 封号，解封多用，管理员用
     * @param userId
     * @param statusCode
     * @param request
     * @return
     */
    @GetMapping("/suspend")
    public BaseResponse<Boolean> suspendUser(long userId, int statusCode, HttpServletRequest request){
        if (!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        if (userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User user = userService.getById(userId);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (statusCode == SUSPENDED_USER){
            userQueryWrapper.eq("userStatus",SUSPENDED_USER);
        }
        if (statusCode == DEFAULT_USER){
            userQueryWrapper.eq("userStatus",DEFAULT_USER);
        }

        boolean result = userService.update(user, userQueryWrapper);

        if (!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据库修改失败");
        }
        return Results.success(true);
    }

    /**
     * 查找多个用户，通过分类分页查询
     * @param userQuery
     * @param request
     * @return
     */
    @GetMapping("/get/list/user/page")
    public BaseResponse<List<UserVo>> getListUser(UserQuery userQuery, HttpServletRequest request){
        if (!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        if (userQuery.getPageNumber() <= 0 || userQuery.getPageSize() <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        if (userQuery.getUserRole() == AUTHOR_ROLE){
            userQueryWrapper.eq("userRole",AUTHOR_ROLE);
        }
        // todo 不能查出管理员（暂定）
        if (StringUtils.isNotBlank(userQuery.getNickName())){
            userQueryWrapper.like("nickName",userQuery.getNickName());
        }

        Page<User> page = userService.page(new Page<>(userQuery.getPageNumber(), userQuery.getPageSize() ), userQueryWrapper);

        return Results.success(page.getRecords().stream().map(user -> userService.getSafeUser(user)).collect(Collectors.toList()));
    }

    /**
     * 获取未审核的作品
     * @param pageSize
     * @param pageNumber
     * @param request
     * @return
     */
    @GetMapping("/get/list/text/page")
    public BaseResponse<List<TextVo>> getListText(int pageSize,int pageNumber,HttpServletRequest request){
        if (!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        if (pageSize <= 0 || pageNumber <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        QueryWrapper<Text> textQueryWrapper = new QueryWrapper<>();
        textQueryWrapper.eq("textStatus",UNDER_REVIEW_TEXT);

        Page<Text> page = textService.page(new Page<>(pageNumber, pageSize), textQueryWrapper);

        return Results.success(page.getRecords().stream().map(textService::getSafeText).collect(Collectors.toList()));
    }

    /**
     * 删除作品
     * @param textId
     * @param request
     * @return
     */
    @GetMapping("/remove/text")
    public BaseResponse<Boolean> removeText(long textId,HttpServletRequest request){
        if (textId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        UpdateWrapper<Text> textUpdateWrapper = new UpdateWrapper<>();
        textUpdateWrapper.eq("textId",textId).set("textStatus",ERROR_TEXT);
        boolean result = textService.update(textUpdateWrapper);

        if (!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据库修改失败");
        }
        return Results.success(true);
    }

}
