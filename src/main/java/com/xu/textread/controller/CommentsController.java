package com.xu.textread.controller;

import com.xu.textread.common.BaseResponse;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.Results;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.model.request.CommentsAddRequest;
import com.xu.textread.model.request.CommentsUpdateRequest;
import com.xu.textread.service.CommentsService;
import com.xu.textread.service.UserService;
import com.xu.textread.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author xyc
 * @CreteDate 2023/2/17 15:02
 * 评论表
 **/
@Controller
@ResponseBody
@RequestMapping("/comments")
public class CommentsController {


    @Resource
    private UserService userService;

    @Resource
    private CommentsService commentsService;

    @PostMapping("/add")
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

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteComments(long userId, long commentId, HttpServletRequest request) {
        if (!NumberUtils.isNumberLessZero(userId, commentId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (!userService.isMe(userId,request)) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是本人操作");
        }

        boolean result = commentsService.removeById(commentId);

        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        return Results.success(true);
    }

    @PostMapping("/update")
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
