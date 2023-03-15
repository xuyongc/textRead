package com.xu.textread.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xu.textread.common.BaseResponse;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.Results;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.model.domain.Favorites;
import com.xu.textread.model.domain.TextLike;
import com.xu.textread.model.request.TextLikeRequest;
import com.xu.textread.model.vo.TextVo;
import com.xu.textread.service.TextLikeService;
import com.xu.textread.service.UserService;
import com.xu.textread.utils.BeanUtil;
import com.xu.textread.utils.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author xyc
 * @CreteDate 2023/2/17 15:03
 * 点赞表
 **/
@Controller
@ResponseBody
@RequestMapping("/textLike")
public class TextLikeController {

    @Resource
    private TextLikeService textLikeService;

    /**
     * 添加
     *
     * @param textLikeRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addTextLike(@RequestBody TextLikeRequest textLikeRequest, HttpServletRequest request) {

        if (textLikeRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Long result = textLikeService.add(textLikeRequest, request);

        if (result < 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库插入失败");
        }

        return Results.success(result);
    }

    /**
     * 判断是否点过赞
     *
     * @param userId
     * @param textId
     * @return
     */
    @GetMapping("/is")
    public BaseResponse<Boolean> isFavorite(Long userId, Long textId) {
        if (!NumberUtils.isNumberLessZero(userId, textId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // todo 升级为redis或者本地缓存
        long count = textLikeService.count(new QueryWrapper<TextLike>().eq("userId", userId).eq("textId", textId));

        return Results.success(count > 0);
    }

}
