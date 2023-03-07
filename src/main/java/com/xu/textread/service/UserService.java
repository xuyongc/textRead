package com.xu.textread.service;

import com.xu.textread.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.textread.model.request.UserUpdateRequest;
import com.xu.textread.model.vo.UserVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
* @Author xyc
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-01-26 22:12:04
*/
public interface UserService extends IService<User> {


    /**
     * 用户登录
     * @param userAccount
     * @param userPassword
     * @param request
     * @return
     */
    UserVo userLogin(String userAccount, String userPassword, HttpServletRequest request);


    /**
     * 登出
     * @param request
     * @return
     */
    long logout(HttpServletRequest request);

    /**
     * 用户注册
     * @param userAccount
     * @param userPassword
     * @param userCheckPassword
     * @return
     */
    long userRegister(String userAccount, String userPassword, String userCheckPassword);

    /**
     * 用户修改
     * @param user
     * @param loginUser
     * @return
     */
    long updateUser(UserUpdateRequest user, UserVo loginUser);

    /**
     * 删除用户管理员和用户都可以用
     * @param deleteUserId
     * @param loginUser
     * @return
     */
    boolean deleteUser(long deleteUserId, UserVo loginUser);

    /**
     * 获取脱敏用户
     * @param user
     * @return
     */
    UserVo getSafeUser(User user);

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    UserVo getLoginUser(HttpServletRequest request);

    /**
     * 上传图片
     * @param userId
     * @param request
     * @param file
     * @return
     */
    String userAvatarUrlUpload(long userId, HttpServletRequest request, MultipartFile file);

    /**
     * 判断是否为管理员
     * @param user
     * @return
     */
    boolean isAdmin(UserVo user);

    /**
     * 判断是否为管理员
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 获取登录id
     * @param request
     * @return
     */
    long getLoginUserId(HttpServletRequest request);

    /**
     * 文件上传
     * @param request
     * @param file
     * @return
     */
    String upload(HttpServletRequest request, MultipartFile file);

    /**
     * 判断是否是自己
     * @param userId
     * @param request
     * @return
     */
    boolean isMe(Long userId, HttpServletRequest request);
}
