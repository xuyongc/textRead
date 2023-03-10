package com.xu.textread.mapper;

import com.xu.textread.model.domain.Text;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.textread.model.vo.TextViewVo;
import org.apache.ibatis.annotations.Param;

/**
* @Author xyc
* @description 针对表【text(文章表)】的数据库操作Mapper
* @createDate 2023-01-26 22:12:04
* @Entity com.xu.textread.model.domain.Text
*/
public interface TextMapper extends BaseMapper<Text> {

    /**
     * 获取textViewVo
     * @param textId
     * @param userId
     * @return
     */
    TextViewVo selectTextViewVo(@Param("textId") Long textId,@Param("userId") Long userId);
}




