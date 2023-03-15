package com.xu.textread.utils;
import java.util.Date;

import com.xu.textread.model.domain.Comments;
import com.xu.textread.model.vo.CommentsVo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeanUtilTest {

    @Test
    void copyList(){
        List<Comments> comments = new ArrayList<>();

        for (int i = 0;i < 10;i++){
            Comments comment = new Comments();
            comment.setCommentsId(1L);
            comment.setUserId(1L);
            comment.setContent(i + "");
            comment.setTextId(1L);

            comments.add(comment);
        }

        List<CommentsVo> commentsVos = BeanUtil.copyList(comments, new CommentsVo());
        System.out.println(commentsVos);
    }
}