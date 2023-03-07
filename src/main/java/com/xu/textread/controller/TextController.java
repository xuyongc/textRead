package com.xu.textread.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xu.textread.common.BaseResponse;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.PageRequest;
import com.xu.textread.common.Results;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.model.domain.Text;
import com.xu.textread.model.domain.User;
import com.xu.textread.model.dto.TextSearchQuery;
import com.xu.textread.model.request.TextSaveRequest;
import com.xu.textread.model.request.TextUpdateRequest;
import com.xu.textread.model.vo.TextVo;
import com.xu.textread.service.TextService;
import com.xu.textread.service.UserService;
import com.xu.textread.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

import static com.xu.textread.constant.AuthorTextConstant.DEFAULT_TEXT;
import static com.xu.textread.constant.UserRoleConstant.AUTHOR_ROLE;
import static com.xu.textread.constant.UserRoleConstant.DEFAULT_ROLE;

/**
 * @Author xyc
 * @CreteDate 2023/1/29 19:31
 * 文章表
 **/
@Controller
@ResponseBody
@RequestMapping("/text")
@CrossOrigin(origins = {"http://127.0.0.1:5173/"})
public class TextController {

    @Resource
    private TextService textService;

    @Resource
    private UserService userService;


    /**
     * 添加作品
     *
     * @param saveRequest
     * @return
     */
    @PostMapping("/save")
    public BaseResponse<Long> save(@RequestBody TextSaveRequest saveRequest, HttpServletRequest request) {

        if (saveRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long textId = textService.saveText(saveRequest, request);

        if (saveRequest.getUserRole() == DEFAULT_ROLE) {
            // todo 事物处理 防止发表作品之后没成为作者
            UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
            userUpdateWrapper.set("userStatus", AUTHOR_ROLE).eq("userId", saveRequest.getTextAuthorId());
            userService.update(userUpdateWrapper);
        }

        if (textId <= 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库插入失败");
        }

        return Results.success(textId);
    }

    /**
     * 作者自己删除作品
     *
     * @param textId
     * @return
     */
    @GetMapping("/remove")
    public BaseResponse<Boolean> remove(long textId, long userId, HttpServletRequest request) {
        if (textId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // todo 改为下架这个删除和下架容易混淆
        boolean result = textService.removeText(textId, userId, request);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库插入失败");
        }
        return Results.success(true);
    }

    /**
     * 修改作品只能修好title和介绍不然要重新上传
     *
     * @param updateRequest
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody TextUpdateRequest updateRequest, HttpServletRequest request) {
        if (updateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = textService.updateText(updateRequest, request);

        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据库插入失败");
        }
        return Results.success(true);
    }

    /**
     * 搜索查询分页，默认分页，用户查询
     *
     * @param pageRequest
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<TextVo>> textListPage(PageRequest pageRequest) {

        if (pageRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        int pageSize = pageRequest.getPageSize();
        int pageNumber = pageRequest.getPageNumber();

        if (!NumberUtils.isNullAndLessZero(pageSize,pageNumber)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        QueryWrapper<Text> textQueryWrapper = new QueryWrapper<>();
        textQueryWrapper.eq("textStatus",DEFAULT_TEXT);

        Page<Text> textPage = textService.page(new Page<>(pageNumber, pageSize),textQueryWrapper);
        List<TextVo> textVoList = textPage.getRecords().stream().map(text -> textService.getSafeText(text)).collect(Collectors.toList());
        return Results.success(textVoList);
    }

}
