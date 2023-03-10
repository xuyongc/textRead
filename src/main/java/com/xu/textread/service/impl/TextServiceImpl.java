package com.xu.textread.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.constant.FileConstant;
import com.xu.textread.model.domain.Text;
import com.xu.textread.model.domain.User;
import com.xu.textread.model.request.TextSaveRequest;
import com.xu.textread.model.request.TextUpdateRequest;
import com.xu.textread.model.vo.TextViewVo;
import com.xu.textread.model.vo.TextVo;
import com.xu.textread.service.TextService;
import com.xu.textread.mapper.TextMapper;
import com.xu.textread.service.UserService;
import com.xu.textread.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.xu.textread.constant.AuthorTextConstant.DEFAULT_TEXT;

/**
 * @Author xyc
 * @description 针对表【text(文章表)】的数据库操作Service实现
 * @createDate 2023-01-26 22:12:04
 */
@Service
public class TextServiceImpl extends ServiceImpl<TextMapper, Text>
        implements TextService {

    @Resource
    private TextMapper textMapper;

    @Resource
    private UserService userService;

    @Override
    public long saveText(TextSaveRequest saveRequest, HttpServletRequest request) {

        if (saveRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String textIntroduce = saveRequest.getTextIntroduce();
        String textTitle = saveRequest.getTextTitle();

        Long textAuthorId = saveRequest.getTextAuthorId();
        if (textAuthorId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (!textAuthorId.equals(userService.getLoginUserId(request))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"不是本人发表");
        }

        if (StringUtils.isAnyBlank(textTitle, textIntroduce)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String userText = saveRequest.getUserText();
        if (userText == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文章内容不能为空");
        }
        String userTextHtml = saveRequest.getTextHtml();
        if (userTextHtml == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"html文件不能为空");
        }

        if (textIntroduce.length() <= 1) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "介绍过短");
        }

        if (textTitle.length() <= 1) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "作品名字过短");
        }
        // todo 封面地址修改


        Text text = new Text();
        BeanUtils.copyProperties(saveRequest, text);
        return textMapper.insert(text);
    }

    @Override
    public boolean removeText(long textId,long userId,HttpServletRequest request){
        if (textId <= 0 || userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (userId != userService.getLoginUserId(request)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"不是本人发表");
        }

        return this.removeById(textId);
    }

    @Override
    public boolean updateText(TextUpdateRequest updateRequest, HttpServletRequest request) {

        if (updateRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Long textAuthorId = updateRequest.getTextAuthorId();
        if (textAuthorId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (!textAuthorId.equals(userService.getLoginUserId(request))){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"不是本人发表");
        }

        String textTitle = updateRequest.getTextTitle();
        String textIntroduce = updateRequest.getTextIntroduce();
        if (StringUtils.isAnyBlank(textTitle, textIntroduce)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (textIntroduce.length() <= 1) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "介绍过短");
        }

        if (textTitle.length() <= 1) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "作品名字过短");
        }

        Long textId = updateRequest.getTextId();
        if (textId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (this.getById(textId) == null){
            throw new BusinessException(ErrorCode.USER_DATA_ERROR,"数据不存在");
        }

        Text text = new Text();
        BeanUtils.copyProperties(updateRequest,text);
        return this.updateById(text);
    }

    @Override
    public List<TextVo> searchText(String searchText, int pageSize, int pageNumber) {
        if (StringUtils.isAnyBlank(searchText)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        QueryWrapper<Text> textQueryWrapper = new QueryWrapper<>();
        textQueryWrapper.like("textTitle", searchText).eq("textStatus",DEFAULT_TEXT);


        Page<Text> page = this.page(new Page<>(pageNumber, pageSize), textQueryWrapper);

        return page.getRecords().stream().map(this::getSafeText).collect(Collectors.toList());
    }

    @Override
    public TextVo getSafeText(Text text) {
        Long userId = text.getTextAuthorId();
        TextVo textVo = new TextVo();
        User user = userService.getById(userId);
        textVo.setTextAuthorName(user.getNickName());
        BeanUtils.copyProperties(text, textVo);
        return textVo;
    }

    @Override
    public TextViewVo getOneTextView(Long textId, Long userId) {
        if (!NumberUtils.isNumberLessZero(textId,userId)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return textMapper.selectTextViewVo(textId,userId);
    }

}




