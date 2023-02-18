package com.xu.textread.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.textread.common.ErrorCode;
import com.xu.textread.common.exception.BusinessException;
import com.xu.textread.model.domain.User;
import com.xu.textread.model.vo.UserVo;
import com.xu.textread.service.UserService;
import com.xu.textread.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xu.textread.constant.UserRoleConstant.ADMIN_ROLE;
import static com.xu.textread.constant.UserRoleConstant.USER_LOGIN_DATA;

/**
 * @author aniki
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-01-26 22:12:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    public static final String HEAD_PASSWORD = "tr.sql";

    public static final String FOOT_PASSWORD = "byXYC";


    @Override
    public UserVo userLogin(String userAccount, String userPassword, HttpServletRequest request) {


        //判断是否为空
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }


        //判断账号是否大于六
        if (userAccount.length() <= 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //判断密码是否大于八
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 校验账户特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //判断是否存在
        String newPassword = DigestUtils.md5DigestAsHex((HEAD_PASSWORD + userPassword + FOOT_PASSWORD).getBytes());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount).eq("userPassword", newPassword);

        UserVo user = this.getSafeUser(this.getOne(userQueryWrapper));

        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_REGISTER);
        }

        request.getSession().setAttribute(USER_LOGIN_DATA, user);

        return user;
    }

    @Override
    public long logout(HttpServletRequest request) {

        UserVo user = (UserVo) request.getSession().getAttribute(USER_LOGIN_DATA);

        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        request.getSession().removeAttribute(USER_LOGIN_DATA);

        return user.getUserId();
    }

    @Override
    public long userRegister(String userAccount, String userPassword, String userCheckPassword) {
        //判断是否为空
        if (StringUtils.isAnyBlank(userAccount, userPassword, userCheckPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //判断账号是否大于六
        if (userAccount.length() <= 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //判断密码和确认密码否大于八
        if (userPassword.length() < 8 || userCheckPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //判断密码和确认密码是否相等
        if (!userPassword.equals(userCheckPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 校验账户特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        //账户不能重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);


        if (this.getOne(userQueryWrapper) != null) {
            throw new BusinessException(ErrorCode.USER_ALREADY_REGISTERED);
        }

        String newPassword = DigestUtils.md5DigestAsHex((HEAD_PASSWORD + userPassword + FOOT_PASSWORD).getBytes());

        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(newPassword);

        if (!this.save(user)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        //mybatis-plus 创建成功会把id传到user里面
        return user.getUserId();
    }

    @Override
    public long updateUser(User user, UserVo loginUser) {
        Long userId = user.getUserId();
        if (userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (!isAdmin(loginUser) && !user.getUserId().equals(loginUser.getUserId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        User oldUser = userMapper.selectById(userId);
        if (oldUser == null){
            throw new BusinessException(ErrorCode.USER_DATA_ERROR);
        }

        return userMapper.updateById(user);
    }

    @Override
    public boolean deleteUser(long deleteUserId, UserVo loginUser){

        if (deleteUserId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (!isAdmin(loginUser) && deleteUserId != loginUser.getUserId()) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        User deleteUser = userMapper.selectById(deleteUserId);
        if (deleteUser == null){
            throw new BusinessException(ErrorCode.USER_DATA_ERROR);
        }

        // todo 管理员自己删除自己可以删除
        if (deleteUser.getUserRole() == ADMIN_ROLE){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"对方为管理员");
        }

        return this.removeById(deleteUserId);
    }

    @Override
    public UserVo getSafeUser(User user) {

        if (user == null) {
            throw new BusinessException(ErrorCode.USER_DATA_ERROR);
        }
        //用户脱敏
        UserVo safeUser = new UserVo();
        BeanUtils.copyProperties(user, safeUser);
        return safeUser;
    }





    @Override
    public UserVo getLoginUser(HttpServletRequest request) {
        Object userData = request.getSession().getAttribute(USER_LOGIN_DATA);
        if (userData == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return (UserVo) userData;
    }

    @Override
    public boolean isAdmin(UserVo user) {
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return user.getUserRole() == ADMIN_ROLE;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        UserVo user = getLoginUser(request);

        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return user.getUserRole() == ADMIN_ROLE;
    }
}




