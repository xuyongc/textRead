package com.xu.textread.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xu.textread.common.BaseResponse;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.Results;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.model.domain.TextLike;
import com.xu.textread.model.request.TextLikeRequest;
import com.xu.textread.service.TextLikeService;
import com.xu.textread.service.UserService;
import com.xu.textread.utils.BeanUtil;
import com.xu.textread.utils.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author aniki
 * @CreteDate 2023/2/17 15:03
 * 点赞表
 **/
@Controller
@ResponseBody
@RequestMapping("/textLike")
public class TextLikeController {


    @Resource
    private UserService userService;

    @Resource
    private TextLikeService textLikeService;

    @PostMapping("/add")
    public BaseResponse<Boolean> addTextLike(@RequestBody TextLikeRequest textLikeRequest, HttpServletRequest request) {

        Long textId = textLikeRequest.getTextId();
        Long userId = textLikeRequest.getUserId();

        if (!NumberUtils.isNullAndLessZero(userId, textId)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        if (!userId.equals(userService.getLoginUser(request).getUserId())) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是本人操作");
        }


        QueryWrapper<TextLike> textLikeQueryWrapper = new QueryWrapper<>();
        textLikeQueryWrapper.eq("userId", userId).eq("textId", textId);

        if (textLikeService.count(textLikeQueryWrapper) >= 1) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "你已经点过赞");
        }

        TextLike textLike = BeanUtil.copyProperties(textLikeRequest, new TextLike());

        boolean result = textLikeService.save(textLike);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库插入失败");
        }

        return Results.success(true);
    }
}
