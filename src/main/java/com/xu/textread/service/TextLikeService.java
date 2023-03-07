package com.xu.textread.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.textread.model.domain.TextLike;
import com.xu.textread.model.request.TextLikeRequest;
import com.xu.textread.model.vo.TextVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Author xyc
* @description 针对表【TextLike(点赞表)】的数据库操作Service
* @createDate 2023-02-10 13:46:33
*/
public interface TextLikeService extends IService<TextLike> {

    /**
     * 添加
     * @param textLikeRequest
     * @param request
     * @return
     */
    boolean add(TextLikeRequest textLikeRequest, HttpServletRequest request);
}
