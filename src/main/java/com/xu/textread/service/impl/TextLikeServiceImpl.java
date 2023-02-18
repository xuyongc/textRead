package com.xu.textread.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.textread.mapper.TextLikeMapper;
import com.xu.textread.model.domain.TextLike;
import com.xu.textread.service.TextLikeService;
import org.springframework.stereotype.Service;

/**
* @author aniki
* @description 针对表【TextLike(点赞表)】的数据库操作Service实现
* @createDate 2023-02-10 13:46:33
*/
@Service
public class TextLikeServiceImpl extends ServiceImpl<TextLikeMapper, TextLike>
    implements TextLikeService{

}




