package com.xu.textread.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.mapper.FavoritesMapper;
import com.xu.textread.model.domain.Favorites;
import com.xu.textread.model.domain.Text;
import com.xu.textread.model.request.FavoritesUpdateRequest;
import com.xu.textread.model.vo.TextVo;
import com.xu.textread.service.FavoritesService;
import com.xu.textread.service.TextService;
import com.xu.textread.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.xu.textread.constant.CommonConstant.ADD;
import static com.xu.textread.constant.CommonConstant.DELETE;

/**
 * @author aniki
 * @description 针对表【favorites(收藏夹)】的数据库操作Service实现
 * @createDate 2023-02-10 13:46:33
 */
@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites>
        implements FavoritesService {

    @Resource
    private UserService userService;

    @Resource
    private FavoritesMapper favoritesMapper;

    @Resource
    private TextService textService;

    @Override
    public long favoritesUpdate(FavoritesUpdateRequest updateRequest, HttpServletRequest request) {
        Long userId = updateRequest.getUserId();


        if (!userId.equals(userService.getLoginUser(request).getUserId())) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是本人操作");
        }

        Long textId = updateRequest.getTextId();
        QueryWrapper<Favorites> favoritesQueryWrapper = new QueryWrapper<>();
        favoritesQueryWrapper.eq("userId", textId).eq("textId", textId);


        Long updateCode = updateRequest.getUpdateCode();

        Favorites favorites = new Favorites();
        BeanUtils.copyProperties(updateRequest, favorites);

        long result = 0;
        if (updateCode == ADD) {
            if (this.count(favoritesQueryWrapper) != 0) {
                throw new BusinessException(ErrorCode.REQUEST_ERROR, "你已经收藏过");
            }

            result = favoritesMapper.insert(favorites);
        }
        if (updateCode == DELETE) {
            if (this.count(favoritesQueryWrapper) == 0) {
                throw new BusinessException(ErrorCode.REQUEST_ERROR, "你已经未收藏");
            }

            result = favoritesMapper.deleteById(favoritesQueryWrapper);
        }

        return result;
    }

    @Override
    public List<TextVo> favoriteVosList(long userId, HttpServletRequest request) {

        if (userId != userService.getLoginUser(request).getUserId()) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是本人操作");
        }

        QueryWrapper<Favorites> favoritesQueryWrapper = new QueryWrapper<>();
        favoritesQueryWrapper.eq("userId", userId);
        List<Favorites> favoriteList = this.list(favoritesQueryWrapper);

        return favoriteList.stream().map(
                favorite -> {
                    Text text = textService.getById(favorite.getTextId());
                    return textService.getSafeText(text);
                }
        ).collect(Collectors.toList());
    }

}






