package com.xu.textread.service;

import com.xu.textread.model.domain.Favorites;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.textread.model.request.FavoritesUpdateRequest;
import com.xu.textread.model.vo.TextVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author aniki
* @description 针对表【favorites(收藏夹)】的数据库操作Service
* @createDate 2023-02-10 13:46:33
*/
public interface FavoritesService extends IService<Favorites> {

    /**
     * 用户修改
     * @param updateRequest
     * @param request
     * @return
     */
    long favoritesUpdate(FavoritesUpdateRequest updateRequest, HttpServletRequest request);

    /**
     * 查询我的收藏
     * @param userId
     * @param request
     * @return
     */
    List<TextVo> favoriteVosList(long userId, HttpServletRequest request);
}
