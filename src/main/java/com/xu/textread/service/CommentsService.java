package com.xu.textread.service;

import com.xu.textread.model.domain.Comments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.textread.model.request.CommentsAddRequest;
import com.xu.textread.model.request.CommentsUpdateRequest;

import javax.servlet.http.HttpServletRequest;

/**
* @author aniki
* @description 针对表【comments(评论表)】的数据库操作Service
* @createDate 2023-02-10 13:46:33
*/
public interface CommentsService extends IService<Comments> {

    /**
     * 修改评论
     * @param updateRequest
     * @param request
     * @return
     */
    Boolean updateComment(CommentsUpdateRequest updateRequest, HttpServletRequest request);

    /**
     * 添加评论
     * @param addRequest
     * @param request
     * @return
     */
    long AddComment(CommentsAddRequest addRequest, HttpServletRequest request);
}
