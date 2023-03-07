package com.xu.textread.controller;

import com.xu.textread.common.BaseResponse;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.Results;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.model.request.FavoritesUpdateRequest;
import com.xu.textread.model.vo.TextVo;
import com.xu.textread.service.FavoritesService;
import com.xu.textread.utils.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xyc
 * @CreteDate 2023/2/17 15:03
 * 收藏表
 **/
@Controller
@ResponseBody
@RequestMapping("/favorites")
public class FavoritesController {

    @Resource
    private FavoritesService favoritesService;

    /**
     * 修改关注
     * @param favoritesUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
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

    /**
     * 查询收藏列表
     * @param userId
     * @param request
     * @return
     */
    @GetMapping("/list")
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
}
