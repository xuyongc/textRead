package com.xu.textread.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xu.textread.common.BaseResponse;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.Results;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.model.domain.Text;
import com.xu.textread.model.domain.User;
import com.xu.textread.model.vo.UserVo;
import com.xu.textread.service.TextService;
import com.xu.textread.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static com.xu.textread.constant.AuthorTextConstant.DEFAULT_TEXT;
import static com.xu.textread.constant.AuthorTextConstant.UNDER_REVIEW_TEXT;
import static com.xu.textread.constant.UserRoleConstant.AUTHOR_ROLE;
import static com.xu.textread.constant.UserRoleConstant.DEFAULT_ROLE;

/**
 * @Author xyc
 * @CreteDate 2023/2/7 15:20
 * 作者表
 **/
@Controller
@ResponseBody
@RequestMapping("/author")
public class AuthorController {

    @Resource
    private UserService userService;

    @Resource
    private TextService textService;
    /**
     * 成为作者，当你发表一个文章就自动为作者
     * @param userId
     * @param request
     * @return
     */
    @GetMapping("/become")
    public BaseResponse<Boolean> becomeAuthor(long userId, HttpServletRequest request){
        if (userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        UserVo loginUser = userService.getLoginUser(request);
        if (loginUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        if (userService.getById(userId) == null){
            throw new BusinessException(ErrorCode.USER_DATA_ERROR);
        }

        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("userRole",AUTHOR_ROLE);
        return Results.success(userService.update(userUpdateWrapper));
    }

    /**
     * 搜索作品(作者自查)，自己正在审核作品审核作品通用
     * @param authorId
     * @return
     */
    @GetMapping("/search/list")
    public BaseResponse<List<Text>> textList(long authorId,int textStatus ){
        if (authorId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        QueryWrapper<Text> textQueryWrapper = new QueryWrapper<>();
        textQueryWrapper.eq("textAuthorId",authorId);
        if (textStatus == DEFAULT_TEXT){
            textQueryWrapper.eq("textStatus",DEFAULT_TEXT);
        }

        if (textStatus == UNDER_REVIEW_TEXT){
            textQueryWrapper.eq("textStatus",UNDER_REVIEW_TEXT);
        }

        List<Text> textList = textService.list(textQueryWrapper);
        if (textList.isEmpty()){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }

        return Results.success(textList);
    }
}
