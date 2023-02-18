package com.xu.textread.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.mapper.CommentsMapper;
import com.xu.textread.model.domain.Comments;
import com.xu.textread.model.request.CommentsAddRequest;
import com.xu.textread.model.request.CommentsUpdateRequest;
import com.xu.textread.service.CommentsService;


import com.xu.textread.service.UserService;
import com.xu.textread.utils.BeanUtil;
import com.xu.textread.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author aniki
 * @description 针对表【comments(评论表)】的数据库操作Service实现
 * @createDate 2023-02-10 13:46:33
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments>
        implements CommentsService {

    @Resource
    private CommentsMapper commentsMapper;

    @Resource
    private UserService userService;

    @Override
    public Boolean updateComment(CommentsUpdateRequest updateRequest, HttpServletRequest request) {
        Long userId = updateRequest.getUserId();
        Long commentsId = updateRequest.getCommentsId();

        String content = updateRequest.getContent();

        addOrUpdate(userId,commentsId,content,request);

        Comments comments = BeanUtil.copyProperties(updateRequest, new Comments());
        return this.updateById(comments);
    }

    @Override
    public long AddComment(CommentsAddRequest addRequest, HttpServletRequest request) {
        Long userId = addRequest.getUserId();
        Long textId = addRequest.getTextId();

        String content = addRequest.getContent();
        addOrUpdate(userId,textId,content,request);

        Comments comments = BeanUtil.copyProperties(addRequest, new Comments());

        return commentsMapper.insert(comments);
    }

    public void addOrUpdate(long userId,long id1,String content,HttpServletRequest request){
        if (!NumberUtils.isNullAndLessZero(userId, id1)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (StringUtils.isAnyBlank(content)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (userId != userService.getLoginUser(request).getUserId()){
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "不是本人操作");
        }

        if (content.length() > 150) {
            throw new BusinessException(ErrorCode.REQUEST_ERROR, "评论过长");
        }
    }
}




