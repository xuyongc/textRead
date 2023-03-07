package com.xu.textread.service;

import com.xu.textread.model.domain.Text;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.textread.model.request.TextSaveRequest;
import com.xu.textread.model.request.TextUpdateRequest;
import com.xu.textread.model.vo.TextVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
* @Author xyc
* @description 针对表【text(文章表)】的数据库操作Service
* @createDate 2023-01-26 22:12:04
*/
public interface TextService extends IService<Text> {


    /**
     * 保存一篇文章
     * @param saveRequest
     * @param request
     * @return
     */
    long saveText(TextSaveRequest saveRequest, HttpServletRequest request);

    /**
     * 删除作品
     * @param textId
     * @param userId
     * @param request
     * @return
     */
    boolean removeText(long textId, long userId, HttpServletRequest request);

    /**
     * 修改文章
     * @param updateRequest
     * @param request
     * @return
     */
    boolean updateText(TextUpdateRequest updateRequest, HttpServletRequest request);

    /**
     * 搜索图书,一句sql一把梭
     * @param searchText
     * @param pageSize
     * @param pageNumber
     * @return
     */
    List<TextVo> searchText(String searchText, int pageSize, int pageNumber);

    /**
     * 获取安全的text对象
     * @param text
     * @return
     */
    TextVo getSafeText(Text text);
}
