package com.xu.textread.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.mapper.TextLikeMapper;
import com.xu.textread.model.domain.Text;
import com.xu.textread.model.domain.TextLike;
import com.xu.textread.model.request.TextLikeRequest;
import com.xu.textread.model.vo.TextVo;
import com.xu.textread.service.TextLikeService;
import com.xu.textread.service.TextService;
import com.xu.textread.service.UserService;
import com.xu.textread.utils.BeanUtil;
import com.xu.textread.utils.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author xyc
 * @description 针对表【TextLike(点赞表)】的数据库操作Service实现
 * @createDate 2023-02-10 13:46:33
 */
@Service
public class TextLikeServiceImpl extends ServiceImpl<TextLikeMapper, TextLike>
        implements TextLikeService {

    @Resource
    private UserService userService;

    @Resource
    private TextService textService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(TextLikeRequest textLikeRequest, HttpServletRequest request) {
        Long textId = textLikeRequest.getTextId();
        Long userId = textLikeRequest.getUserId();

        if (!NumberUtils.isNullAndLessZero(userId, textId)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        if (!userId.equals(userService.getLoginUserId(request))) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是本人操作");
        }

        QueryWrapper<TextLike> textLikeQueryWrapper = new QueryWrapper<>();
        textLikeQueryWrapper.eq("userId", userId).eq("textId", textId);

        if (this.count(textLikeQueryWrapper) >= 1) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "你已经点过赞");
        }

        TextLike textLike = BeanUtil.copyProperties(textLikeRequest, new TextLike());

        boolean saveResult = this.save(textLike);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库异常");
        }
        UpdateWrapper<Text> textUpdateWrapper = new UpdateWrapper<>();
        textUpdateWrapper.set("textLikeNumber", textService.getById(textId).getTextLikeNumber() + 1).eq("userId", userId);
        boolean updateResult = textService.update(textUpdateWrapper);

        if (!updateResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库异常");
        }
        return true;
    }
}




